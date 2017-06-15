package main.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.dao.DealDAO;
import main.dto.DealDto;
import main.dto.DealResultDto;
import main.dto.EnteredDealDto;
import main.dto.TournamentDto;
import main.dto.TournamentStatus;
import main.entities.Deal;
import main.entities.Pair;
import main.entities.User;
import main.exceptions.EnterDealValidationException;
import main.exceptions.LeadValidationException;
import main.model.deals.CardValue;
import main.model.deals.DealModel;
import main.model.deals.DealsUtils;
import main.model.deals.FullHand;
import main.model.movements.Tables;
import main.utils.DataHash;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DealModuleImpl implements DealModule {

    private static Logger LOGGER = Logger.getLogger(DealModuleImpl.class);

    TournamentModule tournamentModule;
    UserModule userModule;
    DealResultModule dealResultModule;
    PairModule pairModule;
    DealsUtils dealsUtils;
    DealDAO dealDAO;

    @Override
    public void createDeal(DealDto d) {
        try {
            dealDAO.create(transformDeal(d));
        } catch (JsonProcessingException e) {
            LOGGER.debug(e.getMessage(), e);
        }
    }

    @Override
    public void updateDeal(DealDto d) throws JsonProcessingException {
        dealDAO.update(transformDeal(d));
    }

    @Override
    public DealDto getDealByHashedId(String id) {
        Deal deal = dealDAO.getDealById(new DataHash().decode(id));
        DealDto dealDto = transformDeal(deal);
        dealDto.setResults(dealResultModule.getByDealId(id));

        return dealDto;
    }

    @Override
    public Deal transformDeal(DealDto dealDto) throws JsonProcessingException {
        dealsUtils = new DealsUtils();
        Deal deal = new Deal();
        deal.setId(dealDto.getId());
        deal.setTournament(tournamentModule.transformTournament(tournamentModule.getById(dealDto.getTournamentId())));
        deal.setCards(dealsUtils.dealToJson(dealDto.getDealModel()));
        deal.setDealNumber(dealDto.getTourDealNumber());

        return deal;
    }

    @Override
    public DealDto transformDeal(Deal deal) {
        dealsUtils = new DealsUtils();
        DealDto dealDto = new DealDto();
        dealDto.setId(deal.getId());
        dealDto.setTournamentId(deal.getTournament().getId());
        try {
            dealDto.setDealModel(dealsUtils.jsonToDeal(deal.getCards()));
        } catch (IOException e) {
            LOGGER.debug(e.getMessage(), e);
        }
        dealDto.setResults(dealResultModule.getByDealId(new DataHash().encode(deal.getId())));
        dealDto.setHashedId(new DataHash().encode(deal.getId()));
        dealDto.setTourDealNumber(deal.getDealNumber());

        return dealDto;
    }

    @Override
    public DealDto getDealById(String dealId) {
        return transformDeal(dealDAO.getDealById(new DataHash().decode(dealId)));
    }

    @Override
    public boolean isDealVisible(DealDto deal, String login) {
        User user = userModule.getUserByLogin(login);
        TournamentDto tour = tournamentModule.getById(deal.getTournamentId());

        if (user.isJudge() || dealResultModule.didUserPlayedThisDeal(login, deal.getResults())
                || isUserPlayingThisDealNow(login, deal)
                || tour.getStatus() == TournamentStatus.COMPLETED) {
            return true;
        }

        return false;
    }

    private boolean isUserPlayingThisDealNow(String login, DealDto deal) {
        TournamentDto tour = tournamentModule.getById(deal.getTournamentId());
        Pair pair = pairModule.getByPlayerAndTour(login, tour.getHashedId());

        if (pair == null) {
            return false;
        }

        Tables.Table table = tour.getMovement().getMovementTables().getTable().get(pair.getCurrentTable() - 1);

        if (table == null) {
            return false;
        }

        Tables.Table.Rounds.Round round = table.getRounds().getRound().get(tour.getCurrentRound().getRoundNumber());

        if (round == null) {
            return false;
        }

        if (round.getBoards().getFrom().intValue() <= deal.getTourDealNumber() && round.getBoards().getTo().intValue() >= deal.getTourDealNumber()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isEnterResultButtonVisible(DealDto deal, String loggedUser) {
        User user = userModule.getUserByLogin(loggedUser);
        TournamentDto tournament = tournamentModule.getById(deal.getTournamentId());

        if (!dealResultModule.didUserPlayedThisDeal(loggedUser, deal.getResults())
                && tournamentModule.isUserInTournamentPairs(tournament.getHashedId(), user.getLogin())
                && tournament.getStatus() == TournamentStatus.INPROGRESS) {
            return true;
        }

        return false;
    }

    @Override
    public String constructContract(int height, String color, String position, int tricks, int doubleValue) {
        StringBuilder sb = new StringBuilder();
        sb.append(height);
        sb.append(color);
        if (doubleValue == 1) {
            sb.append("x");
        } else if (doubleValue == 2) {
            sb.append("xx");
        }
        sb.append(position);

        int tr = tricks - height - 6;

        if (tr == 0) {
            sb.append("=");
        } else if (tr > 0) {
            sb.append("+").append(tr);
        }
        return sb.toString();
    }

    @Override
    public void createDealsForTournament(TournamentDto tournament) {
        int boards = tournament.getMovement().getBoards();
        for (int i = 0; i < boards; i++) {
            DealDto dealDto = new DealDto();
            dealDto.setTournamentId(tournament.getId());
            dealDto.setTourDealNumber(i + 1);
            createDeal(dealDto);
        }
    }

    @Override
    public List<DealDto> getByTourId(String hashedTourId) {
        List<Deal> deals = dealDAO.getByTourId(new DataHash().decode(hashedTourId));
        List<DealDto> dtos = new ArrayList<>();
        for (Deal d : deals) {
            dtos.add(transformDeal(d));
        }
        return dtos;
    }

    @Override
    public void validateDealResult(DealResultDto dto) throws LeadValidationException {
        List<String> COLORS = Arrays.asList("C", "D", "H", "S");
        String lead = dto.getLead();

        if (lead.length() != 2) {
            throw LeadValidationException.leadValidationException();
        } else {
            char first = lead.toCharArray()[0];
            char second = lead.toCharArray()[1];

            if ((first - '0' < 2 || first - '0' > 9) && first != 'T') {
                throw LeadValidationException.leadValidationException();
            }

            if (!COLORS.contains(new String(new char[]{second}))) {
                throw LeadValidationException.leadValidationException();
            }
        }

    }

    @Override
    public boolean canEnterDeal(String dealId, User user) {
        return tournamentModule.getById(getDealByHashedId(dealId).getTournamentId()).getJudge().getLogin().equals(user.getLogin());
    }

    @Override
    public boolean areResultsVisible(String dealId, User user) {
        DealDto deal = getDealByHashedId(dealId);
        TournamentDto tour = tournamentModule.getById(deal.getTournamentId());
        return tour.getJudge().getLogin().equals(user.getLogin())
                || (tour.containsPlayer(user.getLogin()) && deal.containsUsersResult(user));
    }

    @Override
    public void enterDeal(String hashedDealId, EnteredDealDto deal) throws EnterDealValidationException {
        try {
            DealDto dealDto = getDealByHashedId(hashedDealId);
            dealDto.setDealModel(constructDealModel(deal));
            updateDeal(dealDto);
        } catch (JsonProcessingException e) {
            throw EnterDealValidationException.unknownError(e);
        }
    }

    @Override
    public DealModel constructDealModel(EnteredDealDto deal) throws EnterDealValidationException {
        DealModel dealModel = new DealModel();
        dealModel.setNorth(constructFullHand(deal.getNorthSpades(), deal.getNorthHearts(), deal.getNorthDiamonds(), deal.getNorthClubs()));
        dealModel.setSouth(constructFullHand(deal.getSouthSpades(), deal.getSouthHearts(), deal.getSouthDiamonds(), deal.getSouthClubs()));
        dealModel.setEast(constructFullHand(deal.getEastSpades(), deal.getEastHearts(), deal.getEastDiamonds(), deal.getEastClubs()));
        dealModel.setWest(constructFullHand(deal.getWestSpades(), deal.getWestHearts(), deal.getWestDiamonds(), deal.getWestClubs()));
        validateDealModel(dealModel);
        return dealModel;
    }

    private FullHand constructFullHand(String spades, String hearts, String diamonds, String clubs) {
        FullHand fullHand = new FullHand();
        fullHand.setSpades(cardsToCardValues(spades));
        fullHand.setHearts(cardsToCardValues(hearts));
        fullHand.setDiamonds(cardsToCardValues(diamonds));
        fullHand.setClubs(cardsToCardValues(clubs));
        return fullHand;
    }

    private List<CardValue> cardsToCardValues(String cards) {
        List<CardValue> values = new ArrayList<>();
        char[] chars = cards.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            values.add(CardValue.resolve(String.valueOf(chars[i])));
        }
        return values;
    }

    private void validateDealModel(DealModel dealModel) throws EnterDealValidationException {
        validateColor(dealModel.getNorth().getSpades(), dealModel.getSouth().getSpades(), dealModel.getEast().getSpades(), dealModel.getWest().getSpades());
        validateColor(dealModel.getNorth().getHearts(), dealModel.getSouth().getHearts(), dealModel.getEast().getHearts(), dealModel.getWest().getHearts());
        validateColor(dealModel.getNorth().getDiamonds(), dealModel.getSouth().getDiamonds(), dealModel.getEast().getDiamonds(), dealModel.getWest().getDiamonds());
        validateColor(dealModel.getNorth().getClubs(), dealModel.getSouth().getClubs(), dealModel.getEast().getClubs(), dealModel.getWest().getClubs());
    }

    private void validateColor(List<CardValue> north, List<CardValue> south, List<CardValue> east, List<CardValue> west) throws EnterDealValidationException {
        List<CardValue> all = new ArrayList<>();
        all.addAll(north);
        all.addAll(south);
        all.addAll(east);
        all.addAll(west);
        List<CardValue> values = new LinkedList<>(Arrays.asList(CardValue.values()));


        for (CardValue c : all) {
            if (values.contains(c)) {
                values.remove(c);
            } else {
                throw EnterDealValidationException.wrongContent(new Throwable());
            }
        }

    }

    public DealDAO getDealDAO() {
        return dealDAO;
    }

    public void setDealDAO(DealDAO dealDAO) {
        this.dealDAO = dealDAO;
    }

    public TournamentModule getTournamentModule() {
        return tournamentModule;
    }

    public void setTournamentModule(TournamentModule tournamentModule) {
        this.tournamentModule = tournamentModule;
    }

    public UserModule getUserModule() {
        return userModule;
    }

    public void setUserModule(UserModule userModule) {
        this.userModule = userModule;
    }

    public DealResultModule getDealResultModule() {
        return dealResultModule;
    }

    public void setDealResultModule(DealResultModule dealResultModule) {
        this.dealResultModule = dealResultModule;
    }

    public PairModule getPairModule() {
        return pairModule;
    }

    public void setPairModule(PairModule pairModule) {
        this.pairModule = pairModule;
    }
}

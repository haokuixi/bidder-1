package main.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.dao.DealDAO;
import main.dto.DealDto;
import main.dto.TournamentDto;
import main.dto.TournamentStatus;
import main.entities.Deal;
import main.entities.User;
import main.model.deals.DealsUtils;
import main.utils.DataHash;
import org.apache.log4j.Logger;

import java.io.IOException;

public class DealModuleImpl implements DealModule {

    private static Logger LOGGER = Logger.getLogger(DealModuleImpl.class);

    TournamentModule tournamentModule;
    UserModule userModule;
    DealResultModule dealResultModule;
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
        deal.setTournament(tournamentModule.transformTournament(dealDto.getTournament()));
        deal.setCards(dealsUtils.dealToJson(dealDto.getDealModel()));

        return deal;
    }

    @Override
    public DealDto transformDeal(Deal deal) {
        dealsUtils = new DealsUtils();
        DealDto dealDto = new DealDto();
        dealDto.setId(deal.getId());
        dealDto.setTournament(tournamentModule.transformTournament(deal.getTournament()));
        try {
            dealDto.setDealModel(dealsUtils.jsonToDeal(deal.getCards()));
        } catch (IOException e) {
            LOGGER.debug(e.getMessage(), e);
        }
        dealDto.setResults(dealResultModule.getByDealId(new DataHash().encode(deal.getId())));
        dealDto.setHashedId(new DataHash().encode(deal.getId()));

        return dealDto;
    }

    @Override
    public DealDto getDealById(String dealId) {
        return transformDeal(dealDAO.getDealById(new DataHash().decode(dealId)));
    }

    @Override
    public boolean isDealVisible(DealDto deal, String login) {
        User user = userModule.getUserByLogin(login);

        if (user.isJudge() || dealResultModule.didUserPlayThisDeal(login, deal.getResults())
                || tournamentModule.getById(deal.getTournament().getId()).getStatus() != TournamentStatus.INPROGRESS) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isResultButtonVisible(DealDto deal, String loggedUser) {
        User user = userModule.getUserByLogin(loggedUser);
        TournamentDto tournament = tournamentModule.getById(deal.getTournament().getId());

        if (user.isJudge() || !dealResultModule.didUserPlayThisDeal(loggedUser, deal.getResults())
                || tournamentModule.isUserInTournamentPairs(tournament.getHashedId(), user.getLogin())
                || tournament.getStatus() != TournamentStatus.INPROGRESS) {
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
        } else {
            sb.append("-").append(tr);
        }


        return sb.toString();
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
}

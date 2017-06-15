package main.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.dto.DealDto;
import main.dto.DealResultDto;
import main.dto.EnteredDealDto;
import main.dto.TournamentDto;
import main.entities.Pair;
import main.entities.User;
import main.exceptions.EnterDealValidationException;
import main.exceptions.LeadValidationException;
import main.modules.DealModule;
import main.modules.DealResultModule;
import main.modules.PairModule;
import main.modules.UserModule;
import main.utils.ContractPointsCalculator;
import main.utils.DataHash;

import java.util.List;

public class DealServiceImpl implements DealService {

    DealModule dealModule;
    DealResultModule dealResultModule;
    UserModule userModule;
    PairModule pairModule;

    private static final String NS = "NS";
    private static final String EW = "EW";

    @Override
    public void createDeal(DealDto deal) {
        dealModule.createDeal(deal);
    }

    @Override
    public DealDto getDealById(String dealId) {
        return dealModule.getDealById(dealId);
    }

    @Override
    public List<DealResultDto> getDealResultsByDealId(String dealId) {
        return dealResultModule.getByDealId(dealId);
    }

    @Override
    public void saveDealResult(DealResultDto dealResult) {
        dealResultModule.create(dealResult);
    }

    @Override
    public boolean isDealVisible(DealDto deal, String login) {
        return dealModule.isDealVisible(deal, login);
    }

    @Override
    public boolean isEnterResultButtonVisible(DealDto deal, String loggedUser) {
        return dealModule.isEnterResultButtonVisible(deal, loggedUser);
    }

    @Override
    public void saveDealResult(User user, String color, int height, int tricks, int doubleValue, String position, boolean vulnerable, String hashedDealId, String lead) {
        DealResultDto dealResultDto = new DealResultDto();
        dealResultDto.setContractColor(color);
        dealResultDto.setContract(dealModule.constructContract(height, color, position, tricks, doubleValue));
        dealResultDto.setResult(tricks);
        dealResultDto.setPoints(ContractPointsCalculator.calculatePoints(height, color, doubleValue, vulnerable, tricks));
        dealResultDto.setDeclarerPosition(position);
        dealResultDto.setLead(lead);

        try {
            dealResultDto.setDeal(dealModule.transformDeal(dealModule.getDealByHashedId(hashedDealId)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String hashedTourId = new DataHash().encode(dealModule.getDealById(hashedDealId).getTournamentId());

        Pair pair = pairModule.getByPlayerAndTour(user.getLogin(), hashedTourId);
        if (pair.getCurrentPosition().equals(NS)) {
            dealResultDto.setPairNS(pair);
            dealResultDto.setPairEW(pairModule.getByTourTableAndPosition(hashedTourId, pair.getCurrentTable(), EW));
        } else {
            dealResultDto.setPairEW(pair);
            dealResultDto.setPairNS(pairModule.getByTourTableAndPosition(hashedTourId, pair.getCurrentTable(), NS));
        }


        saveDealResult(dealResultDto);
    }

    @Override
    public void createDealsForTournament(TournamentDto tournament) {
        dealModule.createDealsForTournament(tournament);
    }

    @Override
    public void validateDealResult(DealResultDto dto) throws LeadValidationException {
        dealModule.validateDealResult(dto);
    }

    @Override
    public boolean canEnterDeal(String dealId, User user) {
        return dealModule.canEnterDeal(dealId, user);
    }

    @Override
    public boolean areResultsVisible(String dealId, User user) {
        return dealModule.areResultsVisible(dealId, user);
    }

    @Override
    public void enterDeal(String hashedDealId, EnteredDealDto deal) throws EnterDealValidationException {
        dealModule.enterDeal(hashedDealId, deal);
    }

    public DealModule getDealModule() {
        return dealModule;
    }

    public void setDealModule(DealModule dealModule) {
        this.dealModule = dealModule;
    }

    public DealResultModule getDealResultModule() {
        return dealResultModule;
    }

    public void setDealResultModule(DealResultModule dealResultModule) {
        this.dealResultModule = dealResultModule;
    }

    public UserModule getUserModule() {
        return userModule;
    }

    public void setUserModule(UserModule userModule) {
        this.userModule = userModule;
    }

    public PairModule getPairModule() {
        return pairModule;
    }

    public void setPairModule(PairModule pairModule) {
        this.pairModule = pairModule;
    }
}

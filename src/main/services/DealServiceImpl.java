package main.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.dto.DealDto;
import main.dto.DealResultDto;
import main.entities.Pair;
import main.modules.DealModule;
import main.modules.DealResultModule;
import main.modules.PairModule;
import main.modules.UserModule;
import main.utils.ContractPointsCalculator;

import java.util.List;

public class DealServiceImpl implements DealService {

    DealModule dealModule;
    DealResultModule dealResultModule;
    UserModule userModule;
    PairModule pairModule;

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
    public boolean isResultButtonVisible(DealDto deal, String loggedUser) {
        return dealModule.isResultButtonVisible(deal, loggedUser);
    }

    @Override
    public void saveDealResult(String color, int height, int tricks, int doubleValue, String position, boolean vulnerable, String hashedDealId, Pair pairNS, Pair pairEW) {
        DealResultDto dealResultDto = new DealResultDto();
        dealResultDto.setContractColor(color);
        dealResultDto.setContract(dealModule.constructContract(height, color, position, tricks, doubleValue));
        dealResultDto.setResult(tricks);
        dealResultDto.setPoints(ContractPointsCalculator.calculatePoints(height, color, doubleValue, vulnerable, tricks));
        dealResultDto.setDeclarerPosition(position);
        try {
            dealResultDto.setDeal(dealModule.transformDeal(dealModule.getDealByHashedId(hashedDealId)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //todo:
        dealResultDto.setDeclarer(userModule.getById(87));
        dealResultDto.setPairEW(pairModule.getById(50));
        dealResultDto.setPairNS(pairModule.getById(101));
        dealResultDto.setLead("2X");

        saveDealResult(dealResultDto);
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

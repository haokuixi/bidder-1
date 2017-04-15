package main.services;

import main.dto.DealDto;
import main.dto.DealResultDto;
import main.entities.Deal;
import main.modules.DealModule;
import main.modules.DealResultModule;

import java.util.List;

public class DealServiceImpl implements DealService {

    DealModule dealModule;
    DealResultModule dealResultModule;

    @Override
    public void createDeal(DealDto deal) {
        dealModule.createDeal(deal);
    }

    @Override
    public Deal getDealById(String dealId) {
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
}
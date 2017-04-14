package main.services;

import main.dto.DealDto;
import main.modules.DealModule;

public class DealServiceImpl implements DealService {

    DealModule dealModule;

    @Override
    public void createDeal(DealDto deal) {
        dealModule.createDeal(deal);
    }

    public DealModule getDealModule() {
        return dealModule;
    }

    public void setDealModule(DealModule dealModule) {
        this.dealModule = dealModule;
    }
}

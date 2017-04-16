package main.services;

import main.dto.DealDto;
import main.dto.DealResultDto;
import main.entities.Deal;

import java.util.List;

public interface DealService {

    void createDeal(DealDto deal);

    DealDto getDealById(String dealId);

    List<DealResultDto> getDealResultsByDealId(String dealId);

    void saveDealResult(DealResultDto dealResult);

    boolean isDealVisible(DealDto deal, String login);
}

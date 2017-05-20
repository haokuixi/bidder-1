package main.modules;

import main.dto.DealResultDto;
import main.entities.DealResult;

import java.util.List;

public interface DealResultModule {

    void create(DealResultDto dealResultDto);

    List<DealResultDto> getByDealId(String dealId);

    DealResultDto transformDealResult(DealResult dealResult);

    DealResult transformDealResult(DealResultDto dealResultDto);

    String resolveContractColor(String text);

    boolean didUserPlayedThisDeal(String login, List<DealResultDto> results);

    boolean didUserPlayedThisDeal(String login, String dealId);
}

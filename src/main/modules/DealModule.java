package main.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.dto.DealDto;
import main.entities.Deal;

import java.io.IOException;

public interface DealModule {

    void createDeal(DealDto d);

    DealDto getDealByHashedId(String id);

    Deal transformDeal(DealDto dealDto) throws JsonProcessingException;

    DealDto transformDeal(Deal deal) throws IOException;

    DealDto getDealById(String dealId);

    boolean isDealVisible(DealDto deal, String login);
}

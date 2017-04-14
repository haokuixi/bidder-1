package main.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.dto.DealDto;
import main.entities.Deal;

import java.io.IOException;

public interface DealModule {

    void createDeal(DealDto d);

    Deal transformDeal(DealDto dealDto) throws JsonProcessingException;

    DealDto transformDeal(Deal deal) throws IOException;
}

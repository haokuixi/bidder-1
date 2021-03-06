package main.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.dto.DealDto;
import main.dto.DealResultDto;
import main.dto.EnteredDealDto;
import main.dto.TournamentDto;
import main.entities.Deal;
import main.entities.User;
import main.exceptions.EnterDealValidationException;
import main.exceptions.LeadValidationException;
import main.model.deals.DealModel;

import java.io.IOException;
import java.util.List;

public interface DealModule {

    void createDeal(DealDto d);

    void updateDeal(DealDto d) throws JsonProcessingException;

    DealDto getDealByHashedId(String id);

    Deal transformDeal(DealDto dealDto) throws JsonProcessingException;

    DealDto transformDeal(Deal deal) throws IOException;

    DealDto getDealById(String dealId);

    boolean isDealVisible(DealDto deal, String login);

    boolean isEnterResultButtonVisible(DealDto deal, String loggedUser);

    String constructContract(int height, String color, String position, int tricks, int doubleValue);

    void createDealsForTournament(TournamentDto tournament);

    List<DealDto> getByTourId(String hashedTourId);

    void validateDealResult(DealResultDto dto) throws LeadValidationException;

    boolean canEnterDeal(String dealId, User user);

    boolean areResultsVisible(String dealId, User user);

    void enterDeal(String hashedDealId, EnteredDealDto deal) throws EnterDealValidationException;

    DealModel constructDealModel(EnteredDealDto deal) throws EnterDealValidationException;
}

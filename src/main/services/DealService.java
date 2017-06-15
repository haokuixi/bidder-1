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

import java.util.List;

public interface DealService {

    void createDeal(DealDto deal);

    DealDto getDealById(String dealId);

    List<DealResultDto> getDealResultsByDealId(String dealId);

    void saveDealResult(DealResultDto dealResult);

    boolean isDealVisible(DealDto deal, String login);

    boolean isEnterResultButtonVisible(DealDto deal, String loggedUser);

    void saveDealResult(User user, String color, int height, int tricks, int doubleValue, String position,
                        boolean vulnerable, String hashedDealId, String lead);

    void createDealsForTournament(TournamentDto tournament);

    void validateDealResult(DealResultDto dto) throws LeadValidationException;

    boolean canEnterDeal(String dealId, User user);

    boolean areResultsVisible(String dealId, User user);

    void enterDeal(String hashedDealId, EnteredDealDto deal) throws EnterDealValidationException;
}

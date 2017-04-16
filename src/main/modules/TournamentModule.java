package main.modules;

import main.dto.TournamentDto;
import main.entities.Pair;
import main.entities.Tournament;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TournamentModule {
    List<Tournament> getTournamentList(int page);

    void saveTournament(TournamentDto tournament);

    void updateTournament(String hashedId, TournamentDto tournament);

    TournamentDto getById(int id);

    TournamentDto getByHashedId(String hashedId);

    Map<TournamentDto, Pair> getByJudge(int id);

    Map<TournamentDto, Pair> getByPlayer(int id);

    Long countTours();

    List<TournamentDto> transformList(List<Tournament> tournaments);

    void setTournamentStartDate(String hashedId, LocalDateTime startDate);

    void setTournamentEndDate(String hashedId, LocalDateTime endDate);

    boolean canUserJoinTournament(String tourId, int userId);

    TournamentDto transformTournament(Tournament tournament);

    Tournament transformTournament(TournamentDto tournamentDto);

    void incrementTournamentRound(String hashedId);

    boolean isUserInTournamentPairs(String hashedId, String login);
}

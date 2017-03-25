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

    void updateTournament(int tourId, TournamentDto tournament);

    TournamentDto getById(int id);

    TournamentDto getByHashedId(String hashedId);

    Map<TournamentDto, Pair> getByJudge(int id);

    Map<TournamentDto, Pair> getByPlayer(int id);

    Long countTours();

    List<TournamentDto> transformList(List<Tournament> tournaments);

    void setTournamentStartDate(int tourId, LocalDateTime startDate);

    void setTournamentEndDate(int tourId, LocalDateTime endDate);
}

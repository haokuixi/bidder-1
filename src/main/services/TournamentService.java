package main.services;

import main.dto.TournamentDto;
import main.entities.Pair;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Transactional
public interface TournamentService {

    void addTournament(TournamentDto t);

    List<TournamentDto> listTournament(int page);

    TournamentDto getById(int id);

    TournamentDto getByHashedId(String hash);

    Map<TournamentDto, Pair> getByJudge(int id);

    Map<TournamentDto, Pair> getByPlayer(int id);

    Long countTours();

    void editTournament(String hashedId, TournamentDto tournamentDto);

    void beginTournament(String hashedId);

    void setTournamentStartDate(String hashedId, LocalDateTime startDate);

    void setTournamentEndDate(String hashedId, LocalDateTime endDate);

    boolean canUserJoinTournament(String tourId, int userId);

    boolean checkTournamentBeforeBegin(String tourId);
}

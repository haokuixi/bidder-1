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

    Map<TournamentDto, Pair> getByJudge(int id);

    Map<TournamentDto, Pair> getByPlayer(int id);

    Long countTours();

    void editTournament(int tourId, TournamentDto tournamentDto);

    void setTournamentStartDate(int tourId, LocalDateTime startDate);

    void setTournamentEndDate(int tourId, LocalDateTime endDate);
}

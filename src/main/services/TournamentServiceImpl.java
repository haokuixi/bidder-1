package main.services;

import main.dto.TournamentDto;
import main.entities.Pair;
import main.entities.Tournament;
import main.modules.TournamentModule;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class TournamentServiceImpl implements TournamentService {

    TournamentModule tournamentModule;

    @Transactional
    public void addTournament(TournamentDto t) {
        tournamentModule.saveTournament(t);
    }

    @Override
    public List<TournamentDto> listTournament(int page) {
        List<Tournament> tournamentList = tournamentModule.getTournamentList(page);
        return tournamentModule.transformList(tournamentList);
    }

    @Override
    public TournamentDto getById(int id) {
        return tournamentModule.getById(id);
    }

    @Override
    public Map<TournamentDto, Pair> getByJudge(int id) {
        return tournamentModule.getByJudge(id);
    }

    @Override
    public Map<TournamentDto, Pair> getByPlayer(int id) {
        return tournamentModule.getByPlayer(id);
    }

    @Override
    public Long countTours() {
        return tournamentModule.countTours();
    }

    @Override
    public void setTournamentStartDate(int tourId, LocalDateTime startDate) {
        tournamentModule.setTournamentStartDate(tourId, startDate);
    }

    @Override
    public void setTournamentEndDate(int tourId, LocalDateTime endDate) {
        tournamentModule.setTournamentEndDate(tourId, endDate);
    }

    public TournamentModule getTournamentModule() {
        return tournamentModule;
    }

    public void setTournamentModule(TournamentModule tournamentModule) {
        this.tournamentModule = tournamentModule;
    }
}

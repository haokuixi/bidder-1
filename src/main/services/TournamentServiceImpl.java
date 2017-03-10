package main.services;

import main.dto.TournamentDto;
import main.entities.Pair;
import main.entities.Tournament;
import main.modules.TournamentModule;

import java.util.List;
import java.util.Map;

public class TournamentServiceImpl implements TournamentService {

    TournamentModule tournamentModule;

    @Override
    public void addTournament(Tournament t) {
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

    public TournamentModule getTournamentModule() {
        return tournamentModule;
    }

    public void setTournamentModule(TournamentModule tournamentModule) {
        this.tournamentModule = tournamentModule;
    }
}

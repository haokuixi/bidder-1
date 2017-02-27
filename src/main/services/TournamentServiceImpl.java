package main.services;

import main.dto.TournamentDto;
import main.entities.Pair;
import main.entities.Tournament;
import main.modules.TournamentModule;
import org.springframework.security.access.method.P;

import java.util.List;
import java.util.Map;

public class TournamentServiceImpl implements TournamentService {

    TournamentModule tournamentModule;

    @Override
    public void addTournament(Tournament t) {
        tournamentModule.saveTournament(t);
    }

    @Override
    public List<Tournament> listTournament() {
        return tournamentModule.getTournamentList();
    }

    @Override
    public TournamentDto getById(int id) {
        return tournamentModule.getById(id);
    }

    @Override
    public Map<Tournament, Pair> getByJudge(int id) {
        return tournamentModule.getByJudge(id);
    }

    @Override
    public Map<Tournament, Pair> getByPlayer(int id) {
        return tournamentModule.getByPlayer(id);
    }

    public TournamentModule getTournamentModule() {
        return tournamentModule;
    }

    public void setTournamentModule(TournamentModule tournamentModule) {
        this.tournamentModule = tournamentModule;
    }
}

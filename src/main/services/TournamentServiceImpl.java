package main.services;

import main.dto.TournamentDto;
import main.entities.Tournament;
import main.modules.TournamentModule;

import java.util.List;

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
    public List<Tournament> getByJudge(int id) {
        return tournamentModule.getByJudge(id);
    }

    @Override
    public List<Tournament> getByPlayer(int id) {
        return tournamentModule.getByPlayer(id);
    }

    public TournamentModule getTournamentModule() {
        return tournamentModule;
    }

    public void setTournamentModule(TournamentModule tournamentModule) {
        this.tournamentModule = tournamentModule;
    }
}

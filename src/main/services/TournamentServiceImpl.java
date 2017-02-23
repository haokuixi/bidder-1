package main.services;

import main.dto.TournamentDto;
import main.entities.Tournament;
import main.modules.TournamentModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public class TournamentServiceImpl implements TournamentService {

    TournamentModule tournamentModule;

    @Transactional
    public void addTournament(Tournament t) {
        tournamentModule.saveTournament(t);
    }

    @Transactional
    public List<Tournament> listTournament() {
        return tournamentModule.getTournamentList();
    }

    @Override
    public TournamentDto getById(int id) {
        return tournamentModule.getById(id);
    }

    public TournamentModule getTournamentModule() {
        return tournamentModule;
    }

    public void setTournamentModule(TournamentModule tournamentModule) {
        this.tournamentModule = tournamentModule;
    }
}

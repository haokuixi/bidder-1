package main.modules;

import main.dao.TournamentDAO;
import main.entities.Tournament;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TournamentModuleImpl implements TournamentModule {

    TournamentDAO tournamentDAO;

    @Override
    public List<Tournament> getTournamentList() {
        return tournamentDAO.listAll();
    }

    @Override
    public void saveTournament(Tournament tournament) {
        tournamentDAO.create(tournament);
    }

    @Override
    public Tournament getById(int id) {
        return tournamentDAO.getById(id);
    }

    public TournamentDAO getTournamentDAO() {
        return tournamentDAO;
    }

    public void setTournamentDAO(TournamentDAO tournamentDAO) {
        this.tournamentDAO = tournamentDAO;
    }
}

package main.modules;

import main.dao.PairDAO;
import main.dao.TournamentDAO;
import main.dao.UserDAO;
import main.dto.TournamentDto;
import main.entities.Tournament;
import main.entities.User;

import java.util.ArrayList;
import java.util.List;

public class TournamentModuleImpl implements TournamentModule {

    TournamentDAO tournamentDAO;
    UserDAO userDAO;
    PairDAO pairDAO;

    @Override
    public List<Tournament> getTournamentList() {
        return tournamentDAO.listAll();
    }

    @Override
    public void saveTournament(Tournament tournament) {
        tournamentDAO.create(tournament);
    }

    @Override
    public TournamentDto getById(int id) {
        TournamentDto tournamentDto = transformTournament(tournamentDAO.getById(id));
        tournamentDto.setPairs(pairDAO.listByTourId(id));
        return tournamentDto;
    }

    @Override
    public List<Tournament> getByJudge(int id) {
        User user = userDAO.getById(id);
        if (user != null) {
            return tournamentDAO.getToursByJudge(user);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Tournament> getByPlayer(int id) {
        User user = userDAO.getById(id);
        if (user != null) {
            return tournamentDAO.getToursByPlayer(user);
        }
        return new ArrayList<>();
    }

    public TournamentDAO getTournamentDAO() {
        return tournamentDAO;
    }

    public void setTournamentDAO(TournamentDAO tournamentDAO) {
        this.tournamentDAO = tournamentDAO;
    }

    public PairDAO getPairDAO() {
        return pairDAO;
    }

    public void setPairDAO(PairDAO pairDAO) {
        this.pairDAO = pairDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private TournamentDto transformTournament(Tournament tournament) {
        TournamentDto tournamentDto = new TournamentDto();
        tournamentDto.setTitle(tournament.getTitle());
        tournamentDto.setJudge(tournament.getJudge());
        return tournamentDto;
    }
}

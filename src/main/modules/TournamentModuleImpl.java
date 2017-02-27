package main.modules;

import main.dao.PairDAO;
import main.dao.TournamentDAO;
import main.dao.UserDAO;
import main.dto.TournamentDto;
import main.entities.Pair;
import main.entities.Tournament;
import main.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<Tournament, Pair> getByJudge(int id) {
        User user = userDAO.getById(id);
        Map<Tournament, Pair> map = null;
        if (user != null) {
            List<Tournament> tours = tournamentDAO.getToursByJudge(user);

            map = new HashMap<>();
            for(Tournament t:tours) {
                map.put(t, new Pair());
            }
        }
        return map;
    }

    @Override
    public Map<Tournament, Pair> getByPlayer(int id) {
        User user = userDAO.getById(id);
        Map<Tournament, Pair> toursByPlayer = new HashMap<>();
        if (user != null) {
            toursByPlayer = tournamentDAO.getToursByPlayer(user);
        }


        return toursByPlayer;
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

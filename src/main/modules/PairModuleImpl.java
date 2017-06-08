package main.modules;

import main.dao.PairDAO;
import main.entities.Pair;
import main.utils.DataHash;

import java.util.List;

public class PairModuleImpl implements PairModule {

    PairDAO pairDAO;
    UserModule userModule;
    TournamentModule tournamentModule;

    @Override
    public List<Pair> getByPlayer(String login) {
        return pairDAO.getByPlayer(userModule.getUserByLogin(login));
    }

    @Override
    public Pair getByPlayerAndTour(String login, String tourHashedId) {
        return pairDAO.getByPlayerAndTour(login, new DataHash().decode(tourHashedId));
    }

    @Override
    public void removeByPlayerAndTour(String login, String tourHashedId) {
        Pair byPlayerAndTour = getByPlayerAndTour(login, tourHashedId);
        pairDAO.remove(byPlayerAndTour);
    }

    @Override
    public void removeById(int id) {
        pairDAO.remove(id);
    }

    @Override
    public void createPair(int playerOne, int playerTwo, String tourId) {
        Pair p = new Pair();
        p.setPlayerOne(userModule.getById(playerOne));
        p.setPlayerTwo(userModule.getById(playerTwo));
        p.setTournament(tournamentModule.transformTournament(tournamentModule.getByHashedId(tourId)));
        pairDAO.create(p);
    }

    @Override
    public Pair getById(int id) {
        return pairDAO.getById(id);
    }

    @Override
    public Pair getByTourTableAndPosition(String tourHashedId, int tableNumber, String position) {
        return pairDAO.getByTourTableAndPosition(new DataHash().decode(tourHashedId), tableNumber, position);
    }

    public PairDAO getPairDAO() {
        return pairDAO;
    }

    public void setPairDAO(PairDAO pairDAO) {
        this.pairDAO = pairDAO;
    }

    public UserModule getUserModule() {
        return userModule;
    }

    public void setUserModule(UserModule userModule) {
        this.userModule = userModule;
    }

    public TournamentModule getTournamentModule() {
        return tournamentModule;
    }

    public void setTournamentModule(TournamentModule tournamentModule) {
        this.tournamentModule = tournamentModule;
    }
}

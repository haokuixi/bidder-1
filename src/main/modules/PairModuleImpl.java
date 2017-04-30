package main.modules;

import main.dao.PairDAO;
import main.entities.Pair;

import java.util.List;

public class PairModuleImpl implements PairModule {

    PairDAO pairDAO;
    UserModule userModule;
    TournamentModule tournamentModule;

    @Override
    public List<Pair> getByPlayer(int id) {
        return pairDAO.getByPlayer(userModule.getById(id));
    }

    @Override
    public Pair getByPlayerAndTour(int playerId, int tourId) {
        return pairDAO.getByPlayerAndTour(playerId, tourId);
    }

    @Override
    public void removeByPlayerAndTour(int playerId, int tourId) {
        Pair byPlayerAndTour = getByPlayerAndTour(playerId, tourId);
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

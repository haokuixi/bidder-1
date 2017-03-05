package main.modules;

import main.dao.PairDAO;
import main.dao.TournamentDAO;
import main.dao.UserDAO;
import main.dto.TournamentDto;
import main.entities.Pair;
import main.entities.Tournament;
import main.entities.User;
import main.utils.DateTimeUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TournamentModuleImpl implements TournamentModule {

    TournamentDAO tournamentDAO;
    UserDAO userDAO;
    PairDAO pairDAO;

    DateTimeUtils dateTimeUtils;

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

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
    public Map<TournamentDto, Pair> getByJudge(int id) {
        User user = userDAO.getById(id);
        Map<TournamentDto, Pair> map = null;
        if (user != null) {
            List<Tournament> tours = tournamentDAO.getToursByJudge(user);

            map = new HashMap<>();
            for(Tournament t:tours) {
                map.put(transformTournament(t), new Pair());
            }
        }
        return map;
    }

    @Override
    public Map<TournamentDto, Pair> getByPlayer(int id) {
        User user = userDAO.getById(id);
        Map<Tournament, Pair> toursByPlayer = new HashMap<>();
        Map<TournamentDto, Pair> resultMap = new HashMap<>();
        if (user != null) {
            toursByPlayer = tournamentDAO.getToursByPlayer(user);
        }

        for(Tournament t:toursByPlayer.keySet()) {
            resultMap.put(transformTournament(t), toursByPlayer.get(t));
        }
        return resultMap;
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
        dateTimeUtils = new DateTimeUtils();
        TournamentDto tournamentDto = new TournamentDto();
        tournamentDto.setId(tournament.getId());
        tournamentDto.setTitle(tournament.getTitle());
        tournamentDto.setJudge(tournament.getJudge());
        tournamentDto.setStartDate(dateTimeUtils.parseDate(tournament.getStartTime(), DATE_TIME_FORMAT));
        tournamentDto.setEndDate(dateTimeUtils.parseDate(tournament.getEndTime(), DATE_TIME_FORMAT));
        return tournamentDto;
    }
}

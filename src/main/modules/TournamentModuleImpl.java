package main.modules;

import main.dao.PairDAO;
import main.dao.TournamentDAO;
import main.dto.TournamentDto;
import main.dto.TournamentMode;
import main.dto.TournamentStatus;
import main.entities.Pair;
import main.entities.Tournament;
import main.entities.User;
import main.utils.DataHash;
import main.utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TournamentModuleImpl implements TournamentModule {

    private static final int TOURS_PER_PAGE = 10;

    private UserModule userModule;
    private TournamentDAO tournamentDAO;
    private PairDAO pairDAO;

    private DateTimeUtils dateTimeUtils;

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    @Override
    public List<Tournament> getTournamentList(int page) {
        List<Tournament> allTours = tournamentDAO.listAll();

        Collections.sort(allTours, (o1, o2) -> {
            if (o1.getStartTime() == null && o2.getStartTime() == null) {
                return 0;
            }
            if (o1.getStartTime() == null) {
                return -1;
            }
            if (o2.getStartTime() == null) {
                return 1;
            }
            return o1.getStartTime().compareTo(o2.getStartTime());
        });

        if (TOURS_PER_PAGE * page <= allTours.size()) {
            int from = TOURS_PER_PAGE * (page - 1) + 1;
            int to = TOURS_PER_PAGE * page;
            return allTours.subList(from, to);
        } else if (TOURS_PER_PAGE * (page - 1) < allTours.size()) {
            int from = 0;
            if (page > 1) {
                from = TOURS_PER_PAGE * (page - 1) + 1;
            }
            int to = allTours.size();
            return allTours.subList(from, to);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void saveTournament(TournamentDto tournament) {
        tournament.setStatus(TournamentStatus.CREATED);
        tournamentDAO.create(transformTournament(tournament));
    }

    @Override
    public void updateTournament(String hashedId, TournamentDto tournament) {
        updateTournament(new DataHash().decode(hashedId), tournament);
    }

    private void updateTournament(int tourId, TournamentDto tournament) {
        dateTimeUtils = new DateTimeUtils();
        Tournament t = tournamentDAO.getById(tourId);

        String startDate = tournament.getStartDate();
        String endDate = tournament.getEndDate();
        LocalDateTime currentStartDate = t.getStartTime();
        LocalDateTime currentEndDate = t.getEndTime();

        if (currentStartDate == null && startDate != null && !startDate.isEmpty()) {
            t.setStartTime(dateTimeUtils.parseDate(startDate, DATE_TIME_FORMAT));
        } else if (currentEndDate == null && endDate != null && !endDate.isEmpty()) {
            t.setEndTime(dateTimeUtils.parseDate(endDate, DATE_TIME_FORMAT));
        }

        if (tournament.getStatus() != null && !tournament.getStatus().getName().equals(t.getStatus())) {
            t.setStatus(tournament.getStatus().getName());
        }

        if (tournament.getTitle() != null && !tournament.getTitle().equals(t.getTitle())) {
            t.setTitle(tournament.getTitle());
        }

        if (tournament.getDescription() != null && !tournament.getDescription().equals(t.getDescription())) {
            t.setDescription(tournament.getDescription());
        }

        if (tournament.getTournamentMode() != null
                && !tournament.getTournamentMode().getName().equals(t.getTournamentMode())) {
            t.setTournamentMode(tournament.getTournamentMode().getName());
        }

        t.setCurrentRound(tournament.getCurrentRound());

        tournamentDAO.update(t);
    }

    @Override
    public TournamentDto getById(int id) {
        TournamentDto tournamentDto = transformTournament(tournamentDAO.getById(id));
        tournamentDto.setPairs(pairDAO.listByTourId(id));
        return tournamentDto;
    }

    @Override
    public TournamentDto getByHashedId(String hashedId) {
        int id = new DataHash().decode(hashedId);
        TournamentDto tournamentDto = transformTournament(tournamentDAO.getById(id));
        tournamentDto.setPairs(pairDAO.listByTourId(id));
        return tournamentDto;
    }

    @Override
    public Map<TournamentDto, Pair> getByJudge(int id) {
        User user = userModule.getById(id);
        Map<TournamentDto, Pair> map = null;
        if (user != null) {
            List<Tournament> tours = tournamentDAO.getToursByJudge(user);

            map = new HashMap<>();
            for (Tournament t : tours) {
                map.put(transformTournament(t), new Pair());
            }
        }
        return map;
    }

    @Override
    public Map<TournamentDto, Pair> getByPlayer(int id) {
        User user = userModule.getById(id);
        Map<Tournament, Pair> toursByPlayer = new HashMap<>();
        Map<TournamentDto, Pair> resultMap = new HashMap<>();
        if (user != null) {
            toursByPlayer = tournamentDAO.getToursByPlayer(user);
        }

        for (Tournament t : toursByPlayer.keySet()) {
            resultMap.put(transformTournament(t), toursByPlayer.get(t));
        }
        return resultMap;
    }

    @Override
    public Long countTours() {
        return tournamentDAO.countTours();
    }

    @Override
    public List<TournamentDto> transformList(List<Tournament> tournaments) {
        List<TournamentDto> result = new ArrayList<>();
        for (Tournament t : tournaments) {
            result.add(transformTournament(t));
        }
        return result;
    }

    @Override
    public void setTournamentStartDate(String hashedId, LocalDateTime startDate) {
        dateTimeUtils = new DateTimeUtils();
        int tourId = new DataHash().decode(hashedId);
        TournamentDto t = getById(tourId);

        if (startDate == null) {
            startDate = LocalDateTime.now();
        }

        t.setStartDate(dateTimeUtils.parseDate(startDate, DATE_TIME_FORMAT));
        t.setStatus(TournamentStatus.INPROGRESS);
        updateTournament(tourId, t);
    }

    @Override
    public void setTournamentEndDate(String hashedId, LocalDateTime endDate) {
        dateTimeUtils = new DateTimeUtils();
        int tourId = new DataHash().decode(hashedId);
        TournamentDto t = getById(tourId);

        if (endDate == null) {
            endDate = LocalDateTime.now();
        }

        t.setEndDate(dateTimeUtils.parseDate(endDate, DATE_TIME_FORMAT));
        t.setStatus(TournamentStatus.COMPLETED);
        updateTournament(tourId, t);
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

    public UserModule getUserModule() {
        return userModule;
    }

    public void setUserModule(UserModule userModule) {
        this.userModule = userModule;
    }

    @Override
    public TournamentDto transformTournament(Tournament tournament) {
        dateTimeUtils = new DateTimeUtils();
        TournamentDto tournamentDto = new TournamentDto();
        tournamentDto.setId(tournament.getId());
        tournamentDto.setTitle(tournament.getTitle());
        tournamentDto.setDescription(tournament.getDescription());
        tournamentDto.setJudge(tournament.getJudge());
        if (tournament.getStartTime() != null) {
            tournamentDto.setStartDate(dateTimeUtils.parseDate(tournament.getStartTime(), DATE_TIME_FORMAT));
        }
        if (tournament.getEndTime() != null) {
            tournamentDto.setEndDate(dateTimeUtils.parseDate(tournament.getEndTime(), DATE_TIME_FORMAT));
        }
        tournamentDto.setTournamentMode(TournamentMode.valueOf(tournament.getTournamentMode().toUpperCase()));
        tournamentDto.setStatus(TournamentStatus.valueOf(tournament.getStatus()));
        tournamentDto.setHashedId(new DataHash().encode(tournament.getId()));
        tournamentDto.setCurrentRound(tournament.getCurrentRound());
        return tournamentDto;
    }

    @Override
    public Tournament transformTournament(TournamentDto tournamentDto) {
        dateTimeUtils = new DateTimeUtils();
        Tournament tournament = new Tournament();
        tournament.setId(tournamentDto.getId());
        tournament.setTitle(tournamentDto.getTitle());
        tournament.setJudge(tournamentDto.getJudge());

        if (tournamentDto.getStartTime() != null) {
            tournament.setStartTime(dateTimeUtils.parseDate(tournamentDto.getStartDate(), DATE_TIME_FORMAT));
        }

        tournament.setTournamentMode(tournamentDto.getTournamentMode().getName());
        tournament.setStatus(tournamentDto.getStatus().getName());
        tournament.setDescription(tournamentDto.getDescription());
        tournament.setCurrentRound(tournamentDto.getCurrentRound());
        return tournament;
    }

    @Override
    public boolean canUserJoinTournament(String tourId, int userId) {
        User user = userModule.getById(userId);

        if (user.isJudge()) {
            return false;
        }

        List<Pair> pairs = pairDAO.listByTourId(new DataHash().decode(tourId));
        ArrayList players = new ArrayList<>(pairs.stream().map(p -> p.getPlayerOne()).collect(Collectors.toList()));
        players.addAll(pairs.stream().map(p -> p.getPlayerTwo()).collect(Collectors.toList()));

        if (containsUser(players, user)) {
            return false;
        }

        List<User> awaitingByTournament = userModule.getAwaitingByTournament(tourId);
        if (containsUser(awaitingByTournament, user)) {
            return false;
        }

        return true;
    }

    @Override
    public void incrementTournamentRound(String hashedId) {
        TournamentDto tournament = getByHashedId(hashedId);
        tournament.setCurrentRound(tournament.getCurrentRound() + 1);
        updateTournament(hashedId, tournament);
    }

    private boolean containsUser(List<User> users, User user) {
        for (User u : users) {
            if (u.getLogin().equals(user.getLogin())) {
                return true;
            }
        }
        return false;
    }
}

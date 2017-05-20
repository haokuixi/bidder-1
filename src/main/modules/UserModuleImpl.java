package main.modules;

import main.dao.UserDAO;
import main.dto.UserDto;
import main.dto.WzbsDto;
import main.entities.AwaitingPlayer;
import main.entities.Pair;
import main.entities.User;
import main.utils.DataHash;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class UserModuleImpl implements UserModule {

    private static final String WZBS_NZ = "NZ";
    private static final String ROLE_USER = "ROLE_USER";
    private static final int USER_PER_PAGE = 10;
    UserDAO userDAO;
    WzbsModule wzbsModule;
    TournamentModule tournamentModule;
    PairModule pairModule;

    @Override
    public List<User> getUserList(int page) {
        List<User> allUsers = userDAO.listAll();
        Collator collator = Collator.getInstance(Locale.ENGLISH);
        if (!allUsers.isEmpty()) {
            Collections.sort(allUsers, (u1, u2) -> collator.compare(u1.getLogin(), u2.getLogin()));
        }

        if (USER_PER_PAGE * page <= allUsers.size()) {
            int from = USER_PER_PAGE * (page - 1) + 1;
            int to = USER_PER_PAGE * page;
            return allUsers.subList(from, to);
        } else if (USER_PER_PAGE * (page - 1) < allUsers.size()) {
            int from = 0;
            if (page > 1) {
                from = USER_PER_PAGE * (page - 1) + 1;
            }
            int to = allUsers.size();
            return allUsers.subList(from, to);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void saveUser(UserDto user) {
        userDAO.create(transformUser(user, false));
    }

    @Override
    public void updateUser(User loggedUser, UserDto user) {
        if (user.getFirstName() != null && !loggedUser.getName().equals(user.getFirstName())) {
            loggedUser.setName(user.getFirstName());
        }
        if (user.getLastName() != null && !loggedUser.getSurname().equals(user.getLastName())) {
            loggedUser.setSurname(user.getLastName());
        }
        if (user.getWzbs() != null && !loggedUser.getWzbs().getShortName().equals(user.getWzbs().getShortName())) {
            loggedUser.setWzbs(wzbsModule.getWzbsByShortName(user.getWzbs().getShortName()));
        }
        if (user.getPzbsId() != null && loggedUser.getPzbsId() != user.getPzbsId()) {
            loggedUser.setPzbsId(user.getPzbsId());
        }
        userDAO.update(loggedUser);
    }

    @Override
    public Long countUsers() {
        return userDAO.countUsers();
    }

    @Override
    public User getById(int id) {
        return userDAO.getById(id);
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public WzbsModule getWzbsModule() {
        return wzbsModule;
    }

    public void setWzbsModule(WzbsModule wzbsModule) {
        this.wzbsModule = wzbsModule;
    }

    public TournamentModule getTournamentModule() {
        return tournamentModule;
    }

    public void setTournamentModule(TournamentModule tournamentModule) {
        this.tournamentModule = tournamentModule;
    }

    public PairModule getPairModule() {
        return pairModule;
    }

    public void setPairModule(PairModule pairModule) {
        this.pairModule = pairModule;
    }

    public User transformUser(UserDto userDto, boolean isValid) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User u;

        if (isValid) {
            u = getUserByLogin(userDto.getLogin());
        } else {
            u = new User();
            u.setLogin(userDto.getLogin());
            u.setName(userDto.getFirstName());
            u.setSurname(userDto.getLastName());
            u.setPassword(passwordEncoder.encode(userDto.getPassword()));
            u.setWzbs(wzbsModule.getWzbsByShortName(userDto.getWzbs().getShortName()));
            u.setJudge(userDto.isJudge());
            u.setRole(ROLE_USER);

            if (!WZBS_NZ.equals(u.getWzbs().getShortName())) {
                u.setPzbsId(userDto.getPzbsId());
            }
        }
        return u;
    }

    public UserDto transformUser(User user) {
        UserDto u = new UserDto();
        u.setLogin(user.getLogin());
        u.setPassword(user.getPassword());
        u.setFirstName(user.getName());
        u.setLastName(user.getSurname());
        u.setRole(user.getRole());
        u.setWzbs(new WzbsDto(user.getWzbs().getShortName()));
        u.setPzbsId(user.getPzbsId());
        return u;
    }

    @Override
    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public boolean isValidUser(String login, String password) {
        return userDAO.isValidUser(login, password);
    }

    @Override
    public List<User> getAwaitingByTournament(String tourId) {
        return userDAO.getAwaitingByTournament(new DataHash().decode(tourId));
    }

    @Override
    public void quitFromTournament(String login, String tourId) {
        userDAO.quitFromTournament(login, tourId);
    }

    @Override
    public void enterIntoTournament(String login, String tourId) {
        AwaitingPlayer awaitingPlayer = new AwaitingPlayer();
        awaitingPlayer.setPlayer(userDAO.getUserByLogin(login));
        awaitingPlayer.setTournament(tournamentModule.transformTournament(tournamentModule.getByHashedId(tourId)));

        userDAO.enterIntoTournament(awaitingPlayer);
    }

    @Override
    public void quitWithPair(String login, String tourId) {
        Pair pair = pairModule.getByPlayerAndTour(login, tourId);
        pairModule.removeByPlayerAndTour(login, tourId);

        if (pair.getPlayerOne().getLogin() == login) {
            enterIntoTournament(pair.getPlayerTwo().getLogin(), tourId);
        } else {
            enterIntoTournament(pair.getPlayerOne().getLogin(), tourId);
        }
    }
}

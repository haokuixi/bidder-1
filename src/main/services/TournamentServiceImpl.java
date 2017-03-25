package main.services;

import main.dto.TournamentDto;
import main.entities.Pair;
import main.entities.Tournament;
import main.modules.TournamentModule;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class TournamentServiceImpl implements TournamentService {

    TournamentModule tournamentModule;

    @Transactional
    public void addTournament(TournamentDto t) {
        tournamentModule.saveTournament(t);
    }

    @Override
    public List<TournamentDto> listTournament(int page) {
        List<Tournament> tournamentList = tournamentModule.getTournamentList(page);
        return tournamentModule.transformList(tournamentList);
    }

    @Override
    public TournamentDto getById(int id) {
        return tournamentModule.getById(id);
    }

    @Override
    public TournamentDto getByHashedId(String hash) {
        return tournamentModule.getByHashedId(hash);
    }

    @Override
    public Map<TournamentDto, Pair> getByJudge(int id) {
        return tournamentModule.getByJudge(id);
    }

    @Override
    public Map<TournamentDto, Pair> getByPlayer(int id) {
        return tournamentModule.getByPlayer(id);
    }

    @Override
    public Long countTours() {
        return tournamentModule.countTours();
    }

    @Override
    public void editTournament(String hashedId, TournamentDto tournamentDto) {
        tournamentModule.updateTournament(hashedId, tournamentDto);
    }

    @Override
    public void setTournamentStartDate(String hashedId, LocalDateTime startDate) {
        tournamentModule.setTournamentStartDate(hashedId, startDate);
    }

    @Override
    public void setTournamentEndDate(String hashedId, LocalDateTime endDate) {
        tournamentModule.setTournamentEndDate(hashedId, endDate);
    }

    public TournamentModule getTournamentModule() {
        return tournamentModule;
    }

    public void setTournamentModule(TournamentModule tournamentModule) {
        this.tournamentModule = tournamentModule;
    }
}

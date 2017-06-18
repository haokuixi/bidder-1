package main.modules;

import main.dao.RoundDAO;
import main.dto.DealDto;
import main.dto.DealResultDto;
import main.dto.RoundDto;
import main.dto.RoundStatus;
import main.dto.TournamentDto;
import main.dto.TournamentMode;
import main.entities.Round;
import main.utils.DataHash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RoundModuleImpl implements RoundModule {

    private RoundDAO roundDAO;
    private TournamentModule tournamentModule;

    @Override
    public void create(Round r) {
        roundDAO.create(r);
    }

    @Override
    public void create(List<Round> rounds) {
        for (Round r : rounds) {
            roundDAO.create(r);
        }
    }

    @Override
    public void beginRound(Round round) {
        changeRoundStatus(round, RoundStatus.INPROGRESS);
        update(round);
    }

    @Override
    public void update(Round round) {
        roundDAO.update(round);
    }

    @Override
    public Round getById(int id) {
        return roundDAO.getById(id);
    }

    @Override
    public Round getById(String id) {
        return roundDAO.getById(new DataHash().decode(id));
    }

    @Override
    public List<Round> getByTourId(int id) {
        return roundDAO.getByTourId(id);
    }

    @Override
    public List<Round> getByTourId(String id) {
        List<Round> rounds = roundDAO.getByTourId(new DataHash().decode(id));
        rounds.sort(Comparator.comparingInt(Round::getRoundNumber));
        return rounds;
    }

    @Override
    public Round transformRound(RoundDto roundDto) {
        Round round = new Round();
        round.setId(roundDto.getId());
        round.setRoundNumber(roundDto.getRoundNumber());
        round.setTournament(tournamentModule.transformTournament(tournamentModule.getById(roundDto.getTournamentId())));
        round.setStatus(roundDto.getStatus().getName());

        return round;
    }

    @Override
    public RoundDto transformRound(Round round) {
        RoundDto roundDto = new RoundDto();
        roundDto.setId(round.getId());
        roundDto.setRoundNumber(round.getRoundNumber());
        roundDto.setTournamentId(round.getTournament().getId());
        roundDto.setStatus(RoundStatus.valueOf(round.getStatus()));
        roundDto.setHashedId(new DataHash().encode(round.getId()));

        return roundDto;
    }

    @Override
    public List<RoundDto> getDtosByTourId(int id) {
        return transformList(getByTourId(id));
    }

    @Override
    public List<RoundDto> getDtosByTourId(String id) {
        return transformList(getByTourId(id));
    }

    @Override
    public void completeRound(Round r) {
        if (TournamentMode.IMPS.getName().equals(r.getTournament().getTournamentMode())) {
            calculateImpsForRound(r);
        } else {
            calculateMaxesForRound(r);
        }
    }

    private void calculateMaxesForRound(Round round) {

    }

    private void calculateImpsForRound(Round r) {

    }

    private List<RoundDto> transformList(List<Round> rounds) {
        List<RoundDto> dtos = new ArrayList<>();

        for (Round r : rounds) {
            dtos.add(transformRound(r));
        }

        Collections.sort(dtos, Comparator.comparingInt(RoundDto::getRoundNumber));

        return dtos;
    }

    private void changeRoundStatus(Round round, RoundStatus roundStatus) {
        round.setStatus(roundStatus.getName());

    }

    public RoundDAO getRoundDAO() {
        return roundDAO;
    }

    public void setRoundDAO(RoundDAO roundDAO) {
        this.roundDAO = roundDAO;
    }

    public TournamentModule getTournamentModule() {
        return tournamentModule;
    }

    public void setTournamentModule(TournamentModule tournamentModule) {
        this.tournamentModule = tournamentModule;
    }
}

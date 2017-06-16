package main.modules;

import main.dao.RoundResultDAO;
import main.dto.RoundResultDto;
import main.entities.RoundResult;
import main.utils.DataHash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RoundResultModuleImpl implements RoundResultModule {

    TournamentModule tournamentModule;
    RoundResultDAO roundResultDAO;

    @Override
    public RoundResult transformRoundResult(RoundResultDto dto) {
        RoundResult roundResult = new RoundResult();
        roundResult.setId(new DataHash().decode(dto.getHashedId()));
        roundResult.setTournament(tournamentModule.transformTournament(dto.getTournament()));
        roundResult.setPair(dto.getPair());
        roundResult.setRoundNumber(dto.getRoundNumber());
        roundResult.setImpResult(dto.getImpResult());
        roundResult.setMaxResult(dto.getMaxResult());

        return roundResult;
    }

    @Override
    public RoundResultDto transformRoundResult(RoundResult roundResult) {
        RoundResultDto dto = new RoundResultDto();
        dto.setHashedId(new DataHash().encode(roundResult.getId()));
        dto.setTournament(tournamentModule.transformTournament(roundResult.getTournament()));
        dto.setPair(roundResult.getPair());
        dto.setRoundNumber(roundResult.getRoundNumber());
        dto.setImpResult(roundResult.getImpResult());
        dto.setMaxResult(roundResult.getMaxResult());

        return dto;
    }

    @Override
    public List<RoundResultDto> getRoundResultsByTourAndRoundNumber(String tourHashedId, int roundNumber) {
        List<RoundResultDto> list = transformList(roundResultDAO.getByTourAndRoundNumber(new DataHash().decode(tourHashedId), roundNumber));
        Collections.sort(list, Comparator.comparingDouble(RoundResultDto::getRoundNumber));

        return list;
    }

    private List<RoundResultDto> transformList(List<RoundResult> list) {
        List<RoundResultDto> dtos = new ArrayList<>();

        for (RoundResult r : list) {
            dtos.add(transformRoundResult(r));
        }

        Collections.sort(dtos, Comparator.comparingDouble(RoundResultDto::getRoundNumber));


        for(int i=1; i<=dtos.size(); i++) {
            dtos.get(i-1).setPosition(i);
        }
        return dtos;
    }

    public TournamentModule getTournamentModule() {
        return tournamentModule;
    }

    public void setTournamentModule(TournamentModule tournamentModule) {
        this.tournamentModule = tournamentModule;
    }

    public RoundResultDAO getRoundResultDAO() {
        return roundResultDAO;
    }

    public void setRoundResultDAO(RoundResultDAO roundResultDAO) {
        this.roundResultDAO = roundResultDAO;
    }
}

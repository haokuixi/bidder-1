package main.modules;

import main.dao.PairDAO;
import main.dao.TournamentDAO;
import main.dto.TournamentDto;
import main.entities.Tournament;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TournamentModuleImpl implements TournamentModule {

    TournamentDAO tournamentDAO;
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

    private TournamentDto transformTournament(Tournament tournament){
        TournamentDto tournamentDto = new TournamentDto();
        tournamentDto.setTitle(tournament.getTitle());
        tournamentDto.setJudge(tournament.getJudge());
        return tournamentDto;
    }
}

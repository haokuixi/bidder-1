package main.dto;

import main.entities.Tournament;
import main.model.deals.DealModel;

public class DealDto {

    private int id;
    private TournamentDto tournament;
    private DealModel dealModel;

    public DealDto() {
        tournament = new TournamentDto();
        dealModel = new DealModel();
    }

    public DealDto(int id, TournamentDto tournament, DealModel dealModel) {
        this.id = id;
        this.tournament = tournament;
        this.dealModel = dealModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TournamentDto getTournament() {
        return tournament;
    }

    public void setTournament(TournamentDto tournament) {
        this.tournament = tournament;
    }

    public DealModel getDealModel() {
        return dealModel;
    }

    public void setDealModel(DealModel dealModel) {
        this.dealModel = dealModel;
    }
}

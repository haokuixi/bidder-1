package main.dto;

import main.model.deals.DealModel;

public class DealDto {

    private int id;
    private String hashedId;
    private TournamentDto tournament;
    private int tournamentRound;
    private DealModel dealModel;

    public DealDto() {
        tournament = new TournamentDto();
        dealModel = new DealModel();
    }

    public DealDto(TournamentDto tournament, int tournamentRound, DealModel dealModel) {
        this.tournament = tournament;
        this.tournamentRound = tournamentRound;
        this.dealModel = dealModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashedId() {
        return hashedId;
    }

    public void setHashedId(String hashedId) {
        this.hashedId = hashedId;
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

    public int getTournamentRound() {
        return tournamentRound;
    }

    public void setTournamentRound(int tournamentRound) {
        this.tournamentRound = tournamentRound;
    }

    @Override
    public String toString() {
        return "DealDto{" +
                "id=" + id +
                ", hashedId='" + hashedId + '\'' +
                ", tournament=" + tournament +
                ", tournamentRound=" + tournamentRound +
                ", dealModel=" + dealModel +
                '}';
    }
}

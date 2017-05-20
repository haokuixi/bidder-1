package main.dto;

import main.model.deals.DealModel;

import java.util.List;

public class DealDto {

    private int id;
    private String hashedId;
    private int tournamentId;
    private DealModel dealModel;
    private List<DealResultDto> results;
    private int tourDealNumber;

    public DealDto() {
        dealModel = new DealModel();
    }

    public DealDto(DealModel dealModel) {
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

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public DealModel getDealModel() {
        return dealModel;
    }

    public void setDealModel(DealModel dealModel) {
        this.dealModel = dealModel;
    }

    public List<DealResultDto> getResults() {
        return results;
    }

    public void setResults(List<DealResultDto> results) {
        this.results = results;
    }

    public int getTourDealNumber() {
        return tourDealNumber;
    }

    public void setTourDealNumber(int tourDealNumber) {
        this.tourDealNumber = tourDealNumber;
    }

    @Override
    public String toString() {
        return "DealDto{" +
                "id=" + id +
                ", hashedId='" + hashedId + '\'' +
                ", tournamentId=" + tournamentId +
                ", dealModel=" + dealModel +
                ", results=" + results +
                ", tourDealNumber=" + tourDealNumber +
                '}';
    }
}

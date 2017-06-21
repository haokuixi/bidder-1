package main.dto;

import main.entities.User;
import main.model.deals.DealModel;

import java.util.List;

public class DealDto {

    private String hashedId;
    private String tournamentHashedId;
    private DealModel dealModel;
    private List<DealResultDto> results;
    private int tourDealNumber;

    public boolean containsUsersResult(User user) {
        for (DealResultDto d : results) {
            if (d.getPairEW().getPlayerOne().getLogin().equals(user.getLogin()) ||
                    d.getPairEW().getPlayerTwo().getLogin().equals(user.getLogin()) ||
                    d.getPairNS().getPlayerOne().getLogin().equals(user.getLogin()) ||
                    d.getPairNS().getPlayerTwo().getLogin().equals(user.getLogin())) {
                return true;
            }
        }
        return false;
    }

    public DealDto() {
        dealModel = new DealModel();
    }

    public DealDto(DealModel dealModel) {
        this.dealModel = dealModel;
    }

    public String getHashedId() {
        return hashedId;
    }

    public void setHashedId(String hashedId) {
        this.hashedId = hashedId;
    }

    public String getTournamentHashedId() {
        return tournamentHashedId;
    }

    public void setTournamentHashedId(String tournamentHashedId) {
        this.tournamentHashedId = tournamentHashedId;
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
                ", hashedId='" + hashedId + '\'' +
                ", tournamentHashedId=" + tournamentHashedId +
                ", dealModel=" + dealModel +
                ", results=" + results +
                ", tourDealNumber=" + tourDealNumber +
                '}';
    }
}

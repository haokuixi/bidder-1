package main.dto;

import main.entities.Pair;
import main.entities.User;

import java.util.List;

public class TournamentDto {

    private int id;
    private String hashedId;
    private String title;
    private String description;
    private User judge;
    private List<Pair> pairs;
    private Pair currentPair;
    private String startDate;
    private String startTime;
    private String endDate;
    private TournamentMode tournamentMode;
    private TournamentStatus status;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getJudge() {
        return judge;
    }

    public void setJudge(User judge) {
        this.judge = judge;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public Pair getCurrentPair() {
        return currentPair;
    }

    public void setCurrentPair(Pair currentPair) {
        this.currentPair = currentPair;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public TournamentMode getTournamentMode() {
        return tournamentMode;
    }

    public void setTournamentMode(TournamentMode tournamentMode) {
        this.tournamentMode = tournamentMode;
    }

    public TournamentStatus getStatus() {
        return status;
    }

    public void setStatus(TournamentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TournamentDto{" +
                "id=" + id +
                ", hashedId='" + hashedId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", judge=" + judge +
                ", pairs=" + pairs +
                ", currentPair=" + currentPair +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endDate='" + endDate + '\'' +
                ", tournamentMode=" + tournamentMode +
                ", status=" + status +
                '}';
    }
}

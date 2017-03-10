package main.dto;

import main.entities.Pair;
import main.entities.User;

import java.util.List;

public class TournamentDto {

    private int id;
    private String title;
    private User judge;
    private List<Pair> pairs;
    private Pair currentPair;
    private String startDate;
    private String endDate;
    private TournamentMode tournamentMode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "TournamentDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", judge=" + judge +
                ", pairs=" + pairs +
                ", currentPair=" + currentPair +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}

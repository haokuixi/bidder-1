package main.dto;

import main.entities.Pair;
import main.entities.User;

import java.util.List;

public class TournamentDto {

    private String title;
    private User judge;
    private List<Pair> pairs;
    private Pair currentPair;

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

    @Override
    public String toString() {
        return "TournamentDto{" +
                "title='" + title + '\'' +
                ", judge=" + judge +
                ", pairs=" + pairs +
                '}';
    }
}

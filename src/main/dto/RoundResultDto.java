package main.dto;

import main.entities.Pair;

import java.math.BigDecimal;

public class RoundResultDto {

    private String hashedId;

    private TournamentDto tournament;

    private Pair pair;

    private int roundNumber;

    private BigDecimal maxResult;

    private BigDecimal impResult;

    private int position;

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

    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pair) {
        this.pair = pair;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public BigDecimal getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(BigDecimal maxResult) {
        this.maxResult = maxResult;
    }

    public BigDecimal getImpResult() {
        return impResult;
    }

    public void setImpResult(BigDecimal impResult) {
        this.impResult = impResult;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "RoundResultDto{" +
                "hashedId='" + hashedId + '\'' +
                ", pair=" + pair +
                ", roundNumber=" + roundNumber +
                ", maxResult=" + maxResult +
                ", impResult=" + impResult +
                ", position=" + position +
                '}';
    }
}

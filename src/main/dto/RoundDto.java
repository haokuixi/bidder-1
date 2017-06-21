package main.dto;

public class RoundDto {

    private String hashedId;

    private int roundNumber;

    private RoundStatus status;

    private int tournamentId;

    public String getHashedId() {
        return hashedId;
    }

    public void setHashedId(String hashedId) {
        this.hashedId = hashedId;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public RoundStatus getStatus() {
        return status;
    }

    public void setStatus(RoundStatus status) {
        this.status = status;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public String toString() {
        return "RoundDto{" +
                ", hashedId='" + hashedId + '\'' +
                ", roundNumber=" + roundNumber +
                ", status=" + status +
                ", tournamentId=" + tournamentId +
                '}';
    }
}

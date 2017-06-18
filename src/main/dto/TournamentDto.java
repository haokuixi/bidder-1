package main.dto;

import main.entities.Pair;
import main.entities.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    private RoundDto currentRound;
    private RoundDto previousRound;
    private MovementDto movement;
    private int rounds;
    private List<RoundDto> fullRounds;
    private List<DealDto> deals;

    public boolean containsPlayer(String login) {
        for (Pair p : pairs) {
            if (p.getPlayerOne().getLogin().equals(login) || p.getPlayerTwo().getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public String getMovementForPair(String userLogin) {
        Pair pair;
        for (Pair p : pairs) {
            if (p.getPlayerOne().getLogin().equals(userLogin) || p.getPlayerTwo().getLogin().equals(userLogin)) {
                pair = p;
                if (pair.getCurrentPosition().equals("NS")) {
                    return movement.getMovementTables().getTable().get(pair.getCurrentTable() - 1).getMovement().getNs();
                } else {
                    return movement.getMovementTables().getTable().get(pair.getCurrentTable() - 1).getMovement().getEw();
                }
            }
        }
        return null;
    }

    public String getPairNames(int table, String position) {
        StringBuilder sb = new StringBuilder();
        for (Pair p : pairs) {
            if (p.getCurrentTable().equals(table) && p.getCurrentPosition().equals(position)) {
                sb.append(p.getPlayerOne().getSurname());
                sb.append(" - ");
                sb.append(p.getPlayerTwo().getSurname());
                break;
            }
        }
        return sb.toString();
    }

    public String getDealByNumber(int number) {
        for (DealDto d : deals) {
            if (d.getTourDealNumber() == number) {
                return d.getHashedId();
            }
        }
        return null;
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

    public RoundDto getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(RoundDto currentRound) {
        this.currentRound = currentRound;
    }

    public RoundDto getPreviousRound() {
        return previousRound;
    }

    public void setPreviousRound(RoundDto previousRound) {
        this.previousRound = previousRound;
    }

    public MovementDto getMovement() {
        return movement;
    }

    public void setMovement(MovementDto movement) {
        this.movement = movement;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public List<RoundDto> getFullRounds() {
        return fullRounds;
    }

    public void setFullRounds(List<RoundDto> fullRounds) {
        this.fullRounds = fullRounds;
    }

    public List<DealDto> getDeals() {
        return deals.stream().sorted(Comparator.comparingInt(DealDto::getTourDealNumber)).collect(Collectors.toList());
    }

    public void setDeals(List<DealDto> deals) {
        this.deals = deals;
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
                ", currentRound=" + currentRound +
                ", movement=" + movement +
                ", rounds=" + rounds +
                '}';
    }
}

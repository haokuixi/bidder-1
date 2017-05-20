package main.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pairs")
@NamedQueries({
        @NamedQuery(name = "getAllPairs", query = "FROM Pair"),
        @NamedQuery(name = "getByTourId", query = "FROM Pair where tournament.id=?"),
        @NamedQuery(name = "getByPlayer", query = "FROM Pair where playerOne=:player or playerTwo=:player"),
        @NamedQuery(name = "getByPlayerAndTour", query = "FROM Pair where (playerOne.login=:player or playerTwo.login=:player) and tournament.id=:tour")
}
)
public class Pair {

    @Id
    @Column(name = "pair_id")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_ne")
    private User playerOne;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_sw")
    private User playerTwo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tournament")
    private Tournament tournament;

    @Column(name = "max_res", columnDefinition = "float4")
    private Double maxResult;

    @Column(name = "imp_res", columnDefinition = "float4")
    private Double impResult;

    @Column(name = "tour_pair_number")
    private Integer tourNumber;

    @Column(name = "current_table")
    private Integer currentTable;

    @Column(name = "current_position")
    private String currentPosition;

    public User getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(User playerOne) {
        this.playerOne = playerOne;
    }

    public User getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(User playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Double getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(Double maxResult) {
        this.maxResult = maxResult;
    }

    public Double getImpResult() {
        return impResult;
    }

    public void setImpResult(Double impResult) {
        this.impResult = impResult;
    }

    public Integer getTourNumber() {
        return tourNumber;
    }

    public void setTourNumber(Integer tourNumber) {
        this.tourNumber = tourNumber;
    }

    public Integer getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(Integer currentTable) {
        this.currentTable = currentTable;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "id=" + id +
                ", playerOne=" + playerOne +
                ", playerTwo=" + playerTwo +
                ", tournament=" + tournament +
                ", maxResult=" + maxResult +
                ", impResult=" + impResult +
                ", tourNumber=" + tourNumber +
                '}';
    }
}

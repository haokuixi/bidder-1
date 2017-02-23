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
        @NamedQuery(name = "getByTourId", query = "FROM Pair where tournament.id=?")
}
)
public class Pair {

    @Id
    @Column(name = "pair_id")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_1")
    private User playerOne;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_2")
    private User playerTwo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tournament")
    private Tournament tournament;

    @Column(name = "max_res", columnDefinition = "float4")
    private Double maxResult;

    @Column(name = "imp_res", columnDefinition = "float4")
    private Double impResult;

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

    @Override
    public String toString() {
        return "Pair{" +
                "id=" + id +
                ", playerOne=" + playerOne +
                ", playerTwo=" + playerTwo +
                ", tournament=" + tournament +
                ", maxResult=" + maxResult +
                ", impResult=" + impResult +
                '}';
    }
}

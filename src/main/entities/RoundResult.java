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
@Table(name = "round_results")
@NamedQueries({
        @NamedQuery(name = "getResultByTourAndRound", query = "from RoundResult where tournament.id=:tour and roundNumber=:roundNumber")
})
public class RoundResult {

    @Id
    @Column(name = "result_id")
    @SequenceGenerator(name = "tour_seq", sequenceName = "tour_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_seq")
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tour_id")
    private Tournament tournament;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pair_id")
    private Pair pair;

    @Column(name = "round_number")
    private int roundNumber;

    @Column(name = "max_res", columnDefinition = "float4")
    private Double maxResult;

    @Column(name = "imp_res", columnDefinition = "float4")
    private Double impResult;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
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
        return "RoundResult{" +
                "id=" + id +
                ", tournament=" + tournament +
                ", pair=" + pair +
                ", roundNumber=" + roundNumber +
                ", maxResult=" + maxResult +
                ", impResult=" + impResult +
                '}';
    }
}

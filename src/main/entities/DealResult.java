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
@Table(name = "deal_results")
@NamedQueries(
        @NamedQuery(name = "getByDealId", query = "FROM DealResult WHERE dealId.id=?")
)
public class DealResult {

    @Id
    @Column(name = "result_id")
    @SequenceGenerator(name = "tour_seq", sequenceName = "tour_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_seq")
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "deal_id")
    private Deal dealId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ns_pair")
    private Pair pairNS;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ew_pair")
    private Pair pairEW;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "declarer")
    private User declarer;

    @Column(name = "declarer_position")
    private String declarerPosition;

    @Column(name = "contract")
    private String contract;

    @Column(name = "lead")
    private String lead;

    @Column(name = "result")
    private int result;

    @Column(name = "points")
    private int points;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Deal getDealId() {
        return dealId;
    }

    public void setDealId(Deal dealId) {
        this.dealId = dealId;
    }

    public Pair getPairNS() {
        return pairNS;
    }

    public void setPairNS(Pair pairNS) {
        this.pairNS = pairNS;
    }

    public Pair getPairEW() {
        return pairEW;
    }

    public void setPairEW(Pair pairEW) {
        this.pairEW = pairEW;
    }

    public User getDeclarer() {
        return declarer;
    }

    public void setDeclarer(User declarer) {
        this.declarer = declarer;
    }

    public String getDeclarerPosition() {
        return declarerPosition;
    }

    public void setDeclarerPosition(String declarerPosition) {
        this.declarerPosition = declarerPosition;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "DealResult{" +
                "id=" + id +
                ", dealId=" + dealId +
                ", pairNS=" + pairNS +
                ", pairEW=" + pairEW +
                ", declarer=" + declarer +
                ", declarerPosition='" + declarerPosition + '\'' +
                ", contract='" + contract + '\'' +
                ", lead='" + lead + '\'' +
                ", result=" + result +
                ", points=" + points +
                '}';
    }
}

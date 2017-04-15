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
@Table(name = "deals")
@NamedQueries(
        @NamedQuery(name = "getById", query = "FROM Deal WHERE id=?")
)
public class Deal {

    @Id
    @Column(name = "deal_id")
    @SequenceGenerator(name = "tour_seq", sequenceName = "tour_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_seq")
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tour_id")
    private Tournament tournament;

    @Column(name = "round")
    private int tournamentRound;

    @Column(name = "cards")
    private String cards;

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

    public int getTournamentRound() {
        return tournamentRound;
    }

    public void setTournamentRound(int tournamentRound) {
        this.tournamentRound = tournamentRound;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", tournament=" + tournament +
                ", tournamentRound=" + tournamentRound +
                ", cards='" + cards + '\'' +
                '}';
    }
}

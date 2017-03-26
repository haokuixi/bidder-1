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
@Table(name = "awaiting_players")
@NamedQueries({
        @NamedQuery(name = "getByTournament", query = "FROM AwaitingPlayer WHERE tournament.id=?"),
        @NamedQuery(name = "getByUserAndTournament", query = "FROM AwaitingPlayer WHERE player.id=? AND tournament.id=?")
})
public class AwaitingPlayer {

    @Id
    @Column(name = "player_id")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private int playerId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player")
    private User player;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tour")
    private Tournament tournament;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public String toString() {
        return "AwaitingPlayer{" +
                "playerId='" + playerId + '\'' +
                ", player=" + player +
                ", tournament=" + tournament +
                '}';
    }
}

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
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tournaments")
@NamedQueries({
        @NamedQuery(name = "getAllTournaments", query = "FROM Tournament"),
        @NamedQuery(name = "getWhereJudge", query = "FROM Tournament where judge=?"),
        @NamedQuery(name = "getWherePlayer", query = "FROM Tournament t, Pair p where p.tournament=t and (p.playerOne=:player or p.playerTwo=:player)")
})
public class Tournament implements Serializable {

    @Id
    @Column(name = "tour_id")
    @SequenceGenerator(name = "tour_seq", sequenceName = "tour_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_seq")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "judge")
    private User judge;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "mode")
    private String tournamentMode;

    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "round")
    private Round currentRound;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "previous_round")
    private Round previousRound;

    @Column(name = "rounds")
    private int rounds;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "movement_id")
    private Movement movement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getTournamentMode() {
        return tournamentMode;
    }

    public void setTournamentMode(String tournamentMode) {
        this.tournamentMode = tournamentMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public Round getPreviousRound() {
        return previousRound;
    }

    public void setPreviousRound(Round previousRound) {
        this.previousRound = previousRound;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", judge=" + judge +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", tournamentMode='" + tournamentMode + '\'' +
                ", status='" + status + '\'' +
                ", currentRound=" + currentRound +
                ", rounds=" + rounds +
                ", movement=" + movement +
                '}';
    }
}

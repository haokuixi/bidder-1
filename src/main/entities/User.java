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

@Entity
@Table(name = "users")
@NamedQueries(
        @NamedQuery(name = "getAllUsers", query="FROM User")
)
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @SequenceGenerator(name="user_seq", sequenceName="user_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pzbs_id")
    private int pzbsId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "wzbs")
    private Wzbs wzbs;

    @Column(name = "judge")
    private boolean judge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPzbsId() {
        return pzbsId;
    }

    public void setPzbsId(int pzbsId) {
        this.pzbsId = pzbsId;
    }

    public Wzbs getWzbs() {
        return wzbs;
    }

    public void setWzbs(Wzbs wzbs) {
        this.wzbs = wzbs;
    }

    public boolean isJudge() {
        return judge;
    }

    public void setJudge(boolean judge) {
        this.judge = judge;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pzbsId=" + pzbsId +
                ", wzbs=" + wzbs +
                ", judge=" + judge +
                '}';
    }
}

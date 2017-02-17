package main.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "getAllUsers", query = "FROM User"),
        @NamedQuery(name = "getByLogin", query = "FROM User where login = ?")
}
)
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @SequenceGenerator(name="user_seq", sequenceName="user_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pzbs_id")
    private int pzbsId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "wzbs")
    private Wzbs wzbs;

    @Column(name = "role")
    private String role;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pzbsId=" + pzbsId +
                ", wzbs=" + wzbs +
                ", role='" + role + '\'' +
                '}';
    }
}

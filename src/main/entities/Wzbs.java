package main.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "wzbs")
@NamedQueries({
        @NamedQuery(name = "getAllWzbs", query="FROM Wzbs"),
        @NamedQuery(name = "getByShortName", query = "SELECT w FROM Wzbs w WHERE w.shortName=:short")
})
public class Wzbs {

    @Id
    @Column(name = "wzbs_id")
    private int id;

    @Column(name = "shortname")
    private String shortName;

    @Column(name = "fullname")
    private String fullName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return shortName;
    }
}

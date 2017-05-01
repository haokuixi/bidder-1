package main.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "movements")
@NamedQueries({
        @NamedQuery(name = "getById", query = "FROM Movement WHERE id=?"),
        @NamedQuery(name = "getByPairsNumber", query = "FROM Movement WHERE pairs=?")
}
)
public class Movement {
    @Id
    @Column(name = "movement_id")
    @SequenceGenerator(name = "tour_seq", sequenceName = "tour_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_seq")
    private int id;

    @Column(name = "pairs")
    private int pairs;

    @Column(name = "movements_file")
    private String xmlFile;

    public int getPairs() {
        return pairs;
    }

    public void setPairs(int pairs) {
        this.pairs = pairs;
    }

    public String getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(String xmlFile) {
        this.xmlFile = xmlFile;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "id=" + id +
                ", pairs=" + pairs +
                ", xmlFile='" + xmlFile + '\'' +
                '}';
    }
}

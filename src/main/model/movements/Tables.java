
package main.model.movements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "table"
})
@XmlRootElement(name = "tables")
public class Tables {

    protected List<Tables.Table> table;

    public List<Tables.Table> getTable() {
        if (table == null) {
            table = new ArrayList<Tables.Table>();
        }
        return this.table;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "movement",
            "rounds"
    })
    public static class Table {

        @XmlElement(required = true)
        protected Tables.Table.Movement movement;
        @XmlElement(required = true)
        protected Tables.Table.Rounds rounds;
        @XmlAttribute(name = "number")
        protected Byte number;

        public Tables.Table.Movement getMovement() {
            return movement;
        }

        public void setMovement(Tables.Table.Movement value) {
            this.movement = value;
        }

        public Tables.Table.Rounds getRounds() {
            return rounds;
        }

        public void setRounds(Tables.Table.Rounds value) {
            this.rounds = value;
        }

        public Byte getNumber() {
            return number;
        }

        public void setNumber(Byte value) {
            this.number = value;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "ns",
                "ew"
        })
        public static class Movement {

            @XmlElement(required = true)
            protected String ns;
            @XmlElement(required = true)
            protected String ew;

            public String getNs() {
                return ns;
            }

            public void setNs(String value) {
                this.ns = value;
            }

            public String getEw() {
                return ew;
            }

            public void setEw(String value) {
                this.ew = value;
            }

        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "round"
        })
        public static class Rounds {

            protected List<Tables.Table.Rounds.Round> round;

            public List<Tables.Table.Rounds.Round> getRound() {
                if (round == null) {
                    round = new ArrayList<Tables.Table.Rounds.Round>();
                }
                return this.round;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "boards"
            })
            public static class Round {

                @XmlElement(required = true)
                protected Tables.Table.Rounds.Round.Boards boards;
                @XmlAttribute(name = "number")
                protected Byte number;

                public Tables.Table.Rounds.Round.Boards getBoards() {
                    return boards;
                }

                public void setBoards(Tables.Table.Rounds.Round.Boards value) {
                    this.boards = value;
                }

                public Byte getNumber() {
                    return number;
                }

                public void setNumber(Byte value) {
                    this.number = value;
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "value"
                })
                public static class Boards {

                    @XmlValue
                    protected String value;
                    @XmlAttribute(name = "from")
                    protected Byte from;
                    @XmlAttribute(name = "to")
                    protected Byte to;

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public Byte getFrom() {
                        return from;
                    }

                    public void setFrom(Byte value) {
                        this.from = value;
                    }

                    public Byte getTo() {
                        return to;
                    }

                    public void setTo(Byte value) {
                        this.to = value;
                    }

                    @Override
                    public String toString() {
                        return "Boards{" +
                                "value='" + value + '\'' +
                                ", from=" + from +
                                ", to=" + to +
                                '}';
                    }
                }

                @Override
                public String toString() {
                    return "Round{" +
                            "boards=" + boards +
                            ", number=" + number +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "Rounds{" +
                        "round=" + round +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Table{" +
                    "movement=" + movement +
                    ", rounds=" + rounds +
                    ", number=" + number +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Tables{" +
                "table=" + table +
                '}';
    }
}

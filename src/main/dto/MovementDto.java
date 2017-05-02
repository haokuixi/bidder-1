package main.dto;

import main.model.movements.Tables;

public class MovementDto {

    int id;

    private int pairs;

    private Tables movementTables;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPairs() {
        return pairs;
    }

    public void setPairs(int pairs) {
        this.pairs = pairs;
    }

    public Tables getMovementTables() {
        return movementTables;
    }

    public void setMovementTables(Tables movementTables) {
        this.movementTables = movementTables;
    }

    @Override
    public String toString() {
        return "MovementDto{" +
                "id=" + id +
                ", pairs=" + pairs +
                ", movementTables=" + movementTables +
                '}';
    }
}

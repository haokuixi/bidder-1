package main.dto;

import main.model.movements.Tables;

public class MovementDto {

    private int pairs;

    private Tables movementTables;

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
}

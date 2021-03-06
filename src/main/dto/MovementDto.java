package main.dto;

import main.model.movements.Tables;

public class MovementDto {

    String hashedId;

    private int pairs;

    private Tables movementTables;

    private int boards;

    private int rounds;

    public String getHashedId() {
        return hashedId;
    }

    public void setHashedId(String hashedId) {
        this.hashedId = hashedId;
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

    public int getBoards() {
        return boards;
    }

    public void setBoards(int boards) {
        this.boards = boards;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return "MovementDto{" +
                ", pairs=" + pairs +
                ", movementTables=" + movementTables +
                ", boards=" + boards +
                ", rounds=" + rounds +
                '}';
    }
}

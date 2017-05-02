package main.dto;

import main.model.movements.Tables;

public class MovementDto {

    int id;

    private int pairs;

    private Tables movementTables;

    private int boards;

    private int rounds;

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
                "id=" + id +
                ", pairs=" + pairs +
                ", movementTables=" + movementTables +
                ", boards=" + boards +
                ", rounds=" + rounds +
                '}';
    }
}

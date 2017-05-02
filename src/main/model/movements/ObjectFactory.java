
package main.model.movements;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public Tables createTables() {
        return new Tables();
    }

    public Tables.Table createTablesTable() {
        return new Tables.Table();
    }

    public Tables.Table.Rounds createTablesTableRounds() {
        return new Tables.Table.Rounds();
    }

    public Tables.Table.Rounds.Round createTablesTableRoundsRound() {
        return new Tables.Table.Rounds.Round();
    }

    public Tables.Table.Movement createTablesTableMovement() {
        return new Tables.Table.Movement();
    }

    public Tables.Table.Rounds.Round.Boards createTablesTableRoundsRoundBoards() {
        return new Tables.Table.Rounds.Round.Boards();
    }

}

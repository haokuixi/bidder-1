package main.model.deals;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;

public class DealModel {

    private FullHand north;
    private FullHand south;
    private FullHand east;
    private FullHand west;

    public FullHand getNorth() {
        return north;
    }

    public void setNorth(FullHand north) {
        this.north = north;
    }

    public FullHand getSouth() {
        return south;
    }

    public void setSouth(FullHand south) {
        this.south = south;
    }

    public FullHand getEast() {
        return east;
    }

    public void setEast(FullHand east) {
        this.east = east;
    }

    public FullHand getWest() {
        return west;
    }

    public void setWest(FullHand west) {
        this.west = west;
    }

    @JsonIgnore
    public DealModel getMockedDeal() {
        DealModel dm = new DealModel();

        FullHand north = new FullHand();
        FullHand south = new FullHand();
        FullHand east = new FullHand();
        FullHand west = new FullHand();

        // north
        north.setSpades(Arrays.asList(CardValue.ACE, CardValue.QUEEN, CardValue.NINE, CardValue.FOUR));
        north.setHearts(Arrays.asList(CardValue.ACE, CardValue.FIVE));
        north.setDiamonds(Arrays.asList(CardValue.KING, CardValue.NINE));
        north.setClubs(Arrays.asList(CardValue.ACE, CardValue.KING, CardValue.QUEEN, CardValue.TEN, CardValue.EIGHT));

        //south
        south.setSpades(Arrays.asList(CardValue.JACK, CardValue.SEVEN, CardValue.THREE));
        south.setHearts(Arrays.asList(CardValue.JACK));
        south.setDiamonds(Arrays.asList(CardValue.ACE, CardValue.QUEEN, CardValue.EIGHT, CardValue.SEVEN, CardValue.FIVE));
        south.setClubs(Arrays.asList(CardValue.JACK, CardValue.NINE, CardValue.SEVEN, CardValue.FOUR));

        //east
        east.setSpades(Arrays.asList(CardValue.KING, CardValue.FIVE, CardValue.TWO));
        east.setHearts(Arrays.asList(CardValue.EIGHT, CardValue.THREE, CardValue.TWO));
        east.setDiamonds(Arrays.asList(CardValue.JACK, CardValue.SIX, CardValue.FOUR, CardValue.TWO));
        east.setClubs(Arrays.asList(CardValue.FIVE, CardValue.THREE, CardValue.TWO));

        //west
        west.setSpades(Arrays.asList(CardValue.TEN, CardValue.EIGHT, CardValue.SIX));
        west.setHearts(Arrays.asList(CardValue.KING, CardValue.QUEEN, CardValue.TEN, CardValue.NINE, CardValue.SEVEN, CardValue.SIX, CardValue.FOUR));
        west.setDiamonds(Arrays.asList(CardValue.TEN, CardValue.THREE));
        west.setClubs(Arrays.asList(CardValue.SIX));

        dm.setNorth(north);
        dm.setSouth(south);
        dm.setEast(east);
        dm.setWest(west);

        return dm;
    }

    @Override
    public String toString() {
        return "DealModel{" +
                "north=" + north +
                ", south=" + south +
                ", east=" + east +
                ", west=" + west +
                '}';
    }
}

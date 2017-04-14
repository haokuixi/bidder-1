package main.model.deals;

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
}

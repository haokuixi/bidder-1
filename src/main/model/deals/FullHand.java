package main.model.deals;

import java.util.List;

public class FullHand {
    private List<CardValue> spades;
    private List<CardValue> hearts;
    private List<CardValue> diamonds;
    private List<CardValue> clubs;

    public List<CardValue> getSpades() {
        return spades;
    }

    public void setSpades(List<CardValue> spades) {
        this.spades = spades;
    }

    public List<CardValue> getHearts() {
        return hearts;
    }

    public void setHearts(List<CardValue> hearts) {
        this.hearts = hearts;
    }

    public List<CardValue> getDiamonds() {
        return diamonds;
    }

    public void setDiamonds(List<CardValue> diamonds) {
        this.diamonds = diamonds;
    }

    public List<CardValue> getClubs() {
        return clubs;
    }

    public void setClubs(List<CardValue> clubs) {
        this.clubs = clubs;
    }

    @Override
    public String toString() {
        return "FullHand{" +
                "spades=" + spades +
                ", hearts=" + hearts +
                ", diamonds=" + diamonds +
                ", clubs=" + clubs +
                '}';
    }
}

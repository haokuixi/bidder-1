package main.model.deals;

public enum CardColour {
    SPADES("SPADES"),
    HEARTS("HEARTS"),
    DIAMONDS("DIAMONDS"),
    CLUBS("CLUBS"),
    NO_TRUMP("NO_TRUMP");

    private String name;

    CardColour(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package main.dto;

public enum TournamentMode {
    IMPS("imps"),
    MAX("max");

    private String name;

    TournamentMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
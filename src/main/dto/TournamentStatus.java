package main.dto;

public enum TournamentStatus {
    CREATED("CREATED"),
    INPROGRESS("INPROGRESS"),
    SUSPENDED("SUSPENDED"),
    COMPLETED("COMPLETED");

    private String name;

    TournamentStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

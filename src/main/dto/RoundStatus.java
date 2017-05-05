package main.dto;

public enum RoundStatus {
    CREATED("CREATED"),
    INPROGRESS("INPROGRESS"),
    COMPLETED("COMPLETED"),
    SUSPENDED("SUSPENDED");

    private String name;

    RoundStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

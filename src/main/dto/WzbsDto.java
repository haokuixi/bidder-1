package main.dto;

public class WzbsDto {
    private String shortName;

    public WzbsDto() {
    }

    public WzbsDto(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "WzbsDto{" +
                "shortName='" + shortName + '\'' +
                '}';
    }
}

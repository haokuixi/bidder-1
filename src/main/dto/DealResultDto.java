package main.dto;

import main.entities.Deal;
import main.entities.Pair;
import main.entities.User;

public class DealResultDto {

    private String hashedId;
    private Deal deal;
    private Pair pairNS;
    private Pair pairEW;
    private String declarerPosition;
    private String contract;
    private String lead;
    private int result;
    private int points;
    private String contractColor;
    private int roundNumber;
    private double maxResult;
    private double impResult;

    public String getHashedId() {
        return hashedId;
    }

    public void setHashedId(String hashedId) {
        this.hashedId = hashedId;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public Pair getPairNS() {
        return pairNS;
    }

    public void setPairNS(Pair pairNS) {
        this.pairNS = pairNS;
    }

    public Pair getPairEW() {
        return pairEW;
    }

    public void setPairEW(Pair pairEW) {
        this.pairEW = pairEW;
    }

    public String getDeclarerPosition() {
        return declarerPosition;
    }

    public void setDeclarerPosition(String declarerPosition) {
        this.declarerPosition = declarerPosition;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getContractColor() {
        return contractColor;
    }

    public void setContractColor(String contractColor) {
        this.contractColor = contractColor;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public double getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(double maxResult) {
        this.maxResult = maxResult;
    }

    public double getImpResult() {
        return impResult;
    }

    public void setImpResult(double impResult) {
        this.impResult = impResult;
    }

    @Override
    public String toString() {
        return "DealResultDto{" +
                ", hashedId='" + hashedId + '\'' +
                ", deal=" + deal +
                ", pairNS=" + pairNS +
                ", pairEW=" + pairEW +
                ", declarerPosition='" + declarerPosition + '\'' +
                ", contract='" + contract + '\'' +
                ", lead='" + lead + '\'' +
                ", result=" + result +
                ", points=" + points +
                '}';
    }
}

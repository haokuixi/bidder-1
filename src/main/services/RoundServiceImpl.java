package main.services;

import main.entities.Round;
import main.modules.RoundModule;

import java.util.List;

public class RoundServiceImpl implements RoundService {

    RoundModule roundModule;

    @Override
    public void create(Round r) {
        roundModule.create(r);
    }

    @Override
    public void create(List<Round> rounds) {
        roundModule.create(rounds);
    }

    @Override
    public void beginRound(Round r) {
        roundModule.beginRound(r);
    }

    @Override
    public Round getById(int id) {
        return roundModule.getById(id);
    }

    @Override
    public Round getById(String id) {
        return roundModule.getById(id);
    }

    @Override
    public List<Round> getByTourId(int id) {
        return roundModule.getByTourId(id);
    }

    @Override
    public List<Round> getByTourId(String id) {
        return roundModule.getByTourId(id);
    }

    public RoundModule getRoundModule() {
        return roundModule;
    }

    public void setRoundModule(RoundModule roundModule) {
        this.roundModule = roundModule;
    }
}

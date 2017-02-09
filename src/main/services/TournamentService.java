package main.services;

import main.entities.Tournament;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TournamentService {

    void addTournament(Tournament t);
    List<Tournament> listTournament();
    Tournament getById(int id);
}

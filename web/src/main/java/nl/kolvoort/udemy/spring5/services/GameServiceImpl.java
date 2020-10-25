package nl.kolvoort.udemy.spring5.services;

import lombok.extern.slf4j.Slf4j;
import nl.kolvoort.udemy.spring5.Game;
import nl.kolvoort.udemy.spring5.MessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    // fields
    private final Game game;
    private final MessageGenerator messageGenerator;

    // constructor
    @Autowired
    public GameServiceImpl(Game game, MessageGenerator messageGenerator){
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    //  init
    @PostConstruct
    public void init(){
        log.info("mainMessage is {}", messageGenerator.getMainMessage());
        log.info("Number to guess is {}", game.getNumber());
    }

    // public methods
    @Override
    public boolean isGameOver() {
        return game.isGameLost() || game.isGameWon();
    }

    @Override
    public String getResultMessage() {
        return messageGenerator.getResultMessage();
    }

    @Override
    public String getMainMessage() {
        return messageGenerator.getMainMessage();
    }

    @Override
    public void checkGuess(int guess) {
        game.setGuess(guess);
        game.check();
    }

    @Override
    public void reset() {
        game.reset();
        this.init();
    }
}

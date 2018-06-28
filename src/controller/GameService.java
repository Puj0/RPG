package controller;

import acters.acters_repository.IActersRepository;
import commands.CommandDispatcher;
import game.Game;
import game.Printer;

import javax.inject.Inject;

public class GameService implements IGameService {

    private IActersRepository actersRepository;
    private int rounds;
    private Game game;

    @Inject
    public GameService(IActersRepository actersRepository, int rounds) {
        this.actersRepository = actersRepository;
        this.rounds = rounds;
    }

    public Game getGame() {
        return game;
    }


    @Override
    public void getAll() {
        actersRepository.getAll();
    }

    @Override
    public void createGame() {
        game = new Game.GameBuilder(rounds)
                .addPrinter(Printer.getInstance())
                .addActers(actersRepository)
                .addCommandDispatcher(new CommandDispatcher())
                .build();
    }

    @Override
    public void startGame() {
        game.runGame();
    }
}

package controller;

import game.DaggerGameComponent;
import game.GameComponent;

import java.util.Scanner;

public class RPGController {

    GameService gameService;

    public void startGame(){
        int rounds = readNumOfRounds();
        GameComponent gameComponent = buildGameComponent(rounds);
        gameService = gameComponent.inject();

        gameService.getAll();
        gameService.createGame();
        gameService.startGame();
    }

    private GameComponent buildGameComponent(int rounds) {
        return DaggerGameComponent.builder()
                .rpgModule(new RpgModule(rounds))
                .build();
    }

    private int readNumOfRounds() {
        System.out.println("Enter how many rounds are going to be played: ");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
}

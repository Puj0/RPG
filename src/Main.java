import game.DaggerGameComponent;
import game.GameComponent;
import game.GameModule;
import game.Game;

import java.util.Scanner;

public class Main {


	public static void main(String[] args) {

        int rounds = readNumOfRounds();
        GameComponent gameComponent = DaggerGameComponent.builder()
				.gameModule(new GameModule(rounds))
				.build();
        Game game = gameComponent.inject();
		game.runGame();

	}

	private static int readNumOfRounds() {
		System.out.println("Enter how many rounds are going to be played: ");
		Scanner in = new Scanner(System.in);
		return in.nextInt();
	}

}



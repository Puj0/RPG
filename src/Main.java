
import DI.DaggerGameComponent;
import DI.GameModule;
import game.GameSession;
import java.util.Scanner;

public class Main {


	public static void main(String[] args) {

        int rounds = readNumOfRounds();
		GameSession gameSession = new GameSession();

        DaggerGameComponent.builder().gameModule(new GameModule(rounds)).build().inject(gameSession);

        gameSession.start();
	}

	private static int readNumOfRounds() {
		System.out.println("Enter how many rounds are going to be played: ");
		Scanner in = new Scanner(System.in);
		return in.nextInt();
	}

}



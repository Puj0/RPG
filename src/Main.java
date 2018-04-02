
import acters.ActersRepository;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		ActersRepository acters = new ActersRepository();
		acters.createCharacters();

		int rounds = readNumOfRounds();
		Game gb = new Game.GameBuilder(rounds)
			.addActers(acters)
			.build();
		gb.runGame();
	}

	private static int readNumOfRounds() {
		System.out.print("Enter how many rounds are going to be played: ");
		Scanner in = new Scanner(System.in);
		return in.nextInt();
	}

}



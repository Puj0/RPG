import acters.Acter;
import acters.ActersRepository;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		ActersRepository acters = new ActersRepository();
		List<Acter> heroes = acters.getHeroes();
		List<Acter> trolls = acters.getTrolls();

		int rounds = readNumOfRounds();
		Game gb = new Game.GameBuilder(rounds)
				.addRace(heroes)
				.addRace(trolls)
				.build();
		gb.runGame();
	}

	private static int readNumOfRounds() {
		System.out.print("Enter how many rounds are going to be played: ");
		Scanner in = new Scanner(System.in);
		return in.nextInt();
	}

}



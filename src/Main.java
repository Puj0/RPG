import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int rounds = readNumOfRounds();

		int currentRound = 1;
		Game initialGame = new Game();
		while (!initialGame.gameDone() && !(currentRound > rounds)){
			initialGame.runRound();
			currentRound++;
		}
		if ((currentRound != (rounds + 1))){
			initialGame.outcome();
			System.out.println("It took " + (currentRound-1) + " rounds.");
		} else {
			System.out.println("Time's up!");
		}
	}

	private static int readNumOfRounds() {
		System.out.print("Enter how many rounds are going to be played: ");
		Scanner in = new Scanner(System.in);
		return in.nextInt();
	}

}



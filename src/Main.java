
public class Main {

	public static void main(String[] args) {

		int rounds = 15;
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


}



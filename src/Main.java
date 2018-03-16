
public class Main {

	public static void main(String[] args) {

		int rounds = 10;
		int currentRound = 1;
		Game initialGame = new Game();
		while (!initialGame.gameDone() && !(currentRound > rounds)){
			initialGame.runRound();
			currentRound++;
		}
		if ((currentRound != (rounds + 1))){
			initialGame.outcome();
		} else {
			System.out.println("Time's up!");
		}
	}


}



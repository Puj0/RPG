import acters.hero.Hero;
import acters.hero.Role;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		Game initialGame = new Game();
		ArrayList heroes = initialGame.getHeroes();
		ArrayList trolls = initialGame.getTrolls();
		ArrayList animals = initialGame.getAnimals();

		initialGame.createCharacters(heroes, trolls, animals);
		Game gameCopy = (Game) DeepCopy.copy(initialGame);

		initialGame.runGame(heroes, trolls, animals);
		gameCopy.runGame(gameCopy.getHeroes(), gameCopy.getTrolls(), gameCopy.getAnimals());

	}


}



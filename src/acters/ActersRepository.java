package acters;

import acters.enemy.Animal;
import acters.enemy.Troll;
import acters.hero.Hero;
import acters.hero.RoleClass;

import javax.inject.Inject;
import java.util.concurrent.ThreadLocalRandom;


public class ActersRepository implements IActersRepository {

    private SortedActersList sortedActers;
    private ThreadLocalRandom random = ThreadLocalRandom.current();

    @Inject
    public ActersRepository() {
        sortedActers = new SortedActersList();
        createCharacters();
    }

    @Override
    public void createCharacters() {

        int numOfHeroes = random.nextInt(124, 127);
        int numOfEnemies = random.nextInt(numOfHeroes, numOfHeroes * 2);

        createHeroes(numOfHeroes);
        createEnemies(numOfEnemies);
    }

    private void createHeroes(int numOfHeroes) {
        for (int i = 0; i < numOfHeroes; i++) {
            Hero newHero = new Hero("Hero " + (i + 1), RoleClass.values()[i % RoleClass.values().length],
                random.nextInt(10, 26), random.nextInt(3, 11),
                random.nextInt(2, 7), random.nextInt(1, 10));
            addActerToSortedActers(newHero);
        }
    }

    private void createEnemies(int numOfEnemies) {
        for (int i = 0; i < numOfEnemies; i++) {
            boolean isTroll = (random.nextInt(1, 11) % 2) == 0;
            if (isTroll) {
                Troll newTroll = new Troll("Troll " + (i + 1), random.nextInt(10, 26),
                    random.nextInt(3, 11), random.nextInt(2, 7),
                    random.nextInt(1, 10));
                addActerToSortedActers(newTroll);
            } else {
                Animal newAnimal = new Animal("Animal " + (i + 1), random.nextInt(5, 16),
                    random.nextInt(0, 6), random.nextInt(2, 5),
                    random.nextInt(1, 5));
                addActerToSortedActers(newAnimal);
            }
        }
    }

    private void addActerToSortedActers(Acter acter) {
        sortedActers.addActer(new ActerWithInitiative(acter));
    }

    public SortedActersList getSortedActers() {
        return sortedActers;
    }

}

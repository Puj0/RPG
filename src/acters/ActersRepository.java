package acters;

import acters.enemy.Animal;
import acters.enemy.Troll;
import acters.hero.Hero;
import acters.hero.RoleClass;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


public class ActersRepository {

    private SortedSet<ActerWithInitiative> sortedActers;
    private ThreadLocalRandom random = ThreadLocalRandom.current();

    public ActersRepository() {
        sortedActers = new TreeSet<>();
        createCharacters();
    }

    public ActersRepository(int anything){
        sortedActers = new TreeSet<>();
    }

    public void createCharacters() {

        int numOfHeroes = random.nextInt(4, 7);
        int numOfEnemies = random.nextInt(numOfHeroes, numOfHeroes * 2);

        createHeroes(numOfHeroes);
        createEnemies(numOfEnemies);
    }

    public void createHeroes(int numOfHeroes) {
        for (int i = 0; i < numOfHeroes; i++) {
            Hero newHero = new Hero("Hero " + (i + 1), RoleClass.values()[i % RoleClass.values().length],
                    random.nextInt(10, 26), random.nextInt(3, 11),
                    random.nextInt(2, 7), random.nextInt(1, 10));
            addActerToSortedActers(newHero);
        }
    }

    public void createEnemies(int numOfEnemies) {
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

    public void addActerToSortedActers(Acter acter) {
        sortedActers.add(new ActerWithInitiative(acter));
    }

    public void printCharacterInitiatives() {
        for (ActerWithInitiative acterWithInitiative : sortedActers) {
            System.out.println(acterWithInitiative.getActer().getName() + " has initiative: "
                    + acterWithInitiative.getInitiative());
        }
    }

    public List<Acter> getHeroes() {
        return sortedActers.stream()
                .filter(a -> a.getActer() instanceof Hero)
                .map(ActerWithInitiative::getActer)
                .collect(Collectors.toList());
    }

    public List<Acter> getTrolls() {
        return sortedActers.stream()
                .filter(a -> a.getActer() instanceof Troll)
                .map(ActerWithInitiative::getActer)
                .collect(Collectors.toList());
    }

    public List<Acter> getAnimals() {
        return sortedActers.stream()
                .filter(a -> a.getActer() instanceof Animal)
                .map(ActerWithInitiative::getActer)
                .collect(Collectors.toList());
    }

    public boolean gameDone() {
        return (getHeroes().isEmpty() || getTrolls().isEmpty());
    }

    public void removeActer(Acter acter) {
        sortedActers.removeIf(acterWithInitiative -> (acterWithInitiative.getActer().equals(acter)));
    }

    public SortedSet<ActerWithInitiative> getSortedActers() {
        return sortedActers;
    }

}

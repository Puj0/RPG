package acters.acters_repository;

import acters.Acter;
import database.ConnectionRPG;
import game.IRandom;
import acters.enemy.Animal;
import acters.enemy.Troll;
import acters.hero.Hero;
import acters.hero.RoleClass;
import game.ThreadRandom;

import java.util.Arrays;


public class ActersRepository implements IActersRepository {

    private SortedActersList sortedActers;
    private IRandom random = new ThreadRandom();
    private ConnectionRPG connectionRPG;

    public ActersRepository(int numberOfHeroes, int range,ConnectionRPG connectionRPG ) {
        this.connectionRPG = connectionRPG;
        sortedActers = new SortedActersList();
        createCharacters(numberOfHeroes, range);
    }

    @Override
    public void createCharacters(int numberOfHeroes, int range) {

        int numOfHeroes = random.nextInt(numberOfHeroes, numberOfHeroes + range + 1);
        int numOfEnemies = random.nextInt(numOfHeroes, numOfHeroes * 2 + 1);

        try {
         connectionRPG.createActerTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
            boolean isTroll = (random.nextInt(1, 11) % 2) == 1;
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

    @Override
    public void addActersToDatabase(){
        Arrays.stream(sortedActers.getArray())
            .map(ActerWithInitiative::getActer)
            .forEach(acter -> {
                RoleClass roleClass = null;
                if (acter instanceof Hero){
                    roleClass = ((Hero) acter).getRoleClass();
                }
                try {
                    connectionRPG.insertValueToActerTable(acter.getName(), roleClass, acter.getHealthPoints(),
                            acter.getAttack(), acter.getDefence(), acter.getInitiative());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }

    @Override
    public SortedActersList getActersFromDatabase(){
        SortedActersList sortedActersList = new SortedActersList();
        connectionRPG.queryActersFromActerTable().stream()
            .forEach(sortedActersList::addActer);
        return sortedActersList;
    }

    private void addActerToSortedActers(Acter acter) {
        sortedActers.addActer(new ActerWithInitiative(acter, random));
    }

    @Override
    public SortedActersList getSortedActers() {
        return sortedActers;
    }

}

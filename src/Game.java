import acters.Acter;
import acters.ActersRepository;
import acters.ActerWithInitiative;
import actions.Attack;
import actions.RunAway;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Game implements Serializable{

    private ThreadLocalRandom random = ThreadLocalRandom.current();
    private ActersRepository acters;
    private ArrayList<Acter> removedActers = new ArrayList<>();
    private int currentRound;
    private int totalRounds;

    private Game(GameBuilder builder){
        this.acters = builder.acters;
        totalRounds = builder.totalRounds;
    }

    public void runGame(){
        acters.printCharacterInitiatives();
        runUntilDone(totalRounds);
    }

    private void runUntilDone(int rounds) {
        for(currentRound = 0; currentRound < rounds; currentRound++){
            if (acters.gameDone()){
                outcome();
                System.out.println("It took " + (currentRound) + " rounds.");
                return;
            }
            runRound();
        }
        if (acters.gameDone()) {
            outcome();
            System.out.println("It took " + (currentRound - 1) + " rounds.");
        } else {
            System.out.println("Time's up!");
        }
    }

    private void runRound(){
        battle();
        retreat();
        cleanUpBattlefield();
        stateAtTheEndOfTheRound();
    }

    private void battle() {
        for (ActerWithInitiative acter : acters.getSortedActers()) {
            if (!removedActers.contains(acter.getActer())){
                fight(acter.getActer());
            }
        }
    }

    private void cleanUpBattlefield() {
        for (Acter acter : removedActers) {
            acters.removeActer(acter);
        }
    }

    private void retreat() {
        for (ActerWithInitiative acter : acters.getSortedActers()) {
            if (acter.getActer().getHealthPoints() < 2 && !removedActers.contains(acter.getActer())){
                new RunAway(acter.getActer());
                removedActers.add(acter.getActer());
            }
        }
    }

    private boolean acterIsAttacking(){
        return (random.nextInt(0,4) > 0);
    }

    private void fight(Acter attacker){

        if (acterIsAttacking()) {
            List<Acter> defenders = createListOfDefenders(attacker);

            if (!defenders.isEmpty()) {
                Acter defender = defenders.get(random.nextInt(0, defenders.size()));
                new Attack(attacker, defender);
                if (defender.getHealthPoints() <= 0) {
                    removedActers.add(defender);
                    System.out.println(defender.getName() + " has died.");
                }
            } else {
                System.out.println(attacker.getName() + " had no one to attack.");
            }
        } else {
            System.out.println(attacker.getName() + " has decided to skip this round.");
        }
    }

    private List<Acter> createListOfDefenders(Acter attacker) {
        return acters.getSortedActers().stream()
                .map(ActerWithInitiative::getActer)
                .filter(a -> !(a.getClass().equals(attacker.getClass())))
                .filter(a -> !(removedActers.contains(a)))
                .collect(Collectors.toList());
    }

    private void stateAtTheEndOfTheRound(){
        System.out.println("End of round " + (currentRound + 1) + ". \n" +
                "Heroes - Trolls - Animals \n" +
                acters.getHeroes().size() + "\t\t" + acters.getTrolls().size() + "\t\t" + acters.getAnimals().size());
    }

    private void outcome() {
        if (acters.getHeroes().isEmpty()) {
            System.out.println("Heroes lost!");
        } else {
            System.out.println("Heroes were victorious");
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static class GameBuilder {
        private ActersRepository acters = new ActersRepository(0);
        private int totalRounds;

        GameBuilder(int rounds){
            this.totalRounds = rounds;
        }

        public GameBuilder addRace(List<Acter> acters){
            for (Acter acter: acters) {
                this.acters.addActerToSortedActers(acter);
            }
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }
}

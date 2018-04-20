import acters.Acter;
import acters.ActerWithInitiative;
import acters.ActersRepository;
import acters.SortedActersList;
import acters.enemy.Animal;
import acters.enemy.Troll;
import acters.hero.Hero;
import commands.*;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Game implements Serializable {

    private ThreadLocalRandom random = ThreadLocalRandom.current();
    private SortedActersList acters;
    private ArrayList<Acter> removedActers = new ArrayList<>();
    private int currentRound;
    private int totalRounds;
    private CommandDispatcher dispatcher = new CommandDispatcher();
    private CommandFactory commandFactory = new CommandFactory();

    private Game(GameBuilder builder) {
        this.acters = builder.acters;
        totalRounds = builder.totalRounds;
    }

    public void runGame() {
        printCharacterInitiatives();
        runUntilDone(totalRounds);
    }

    private void printCharacterInitiatives() {
        for (ActerWithInitiative acterWithInitiative : acters.getArray()) {
            System.out.println(acterWithInitiative.getActer().getName() + " has initiative: "
                    + acterWithInitiative.getInitiative());
        }
    }

    private void runUntilDone(int rounds) {
        for (currentRound = 0; currentRound < rounds; currentRound++) {
            if (gameDone()) {
                break;
            }
            runRound();
        }
        if (gameDone()) {
            outcome();
            System.out.println("It took " + (currentRound - 1) + " rounds.");
        } else {
            System.out.println("Time's up!");
        }
    }

    private boolean gameDone() {
        return Arrays.stream(acters.getArray())
                .filter(acterWithInitiative -> acterWithInitiative.getActer().isMain())
                .map(a -> a.getActer().getClass())
                .distinct()
                .count() < 2;
    }

    public List<Acter> getRace(Class<? extends Acter> acterClass) {
        List<Acter> race = new ArrayList<>();
        for (ActerWithInitiative acterWithInitiative : acters.getArray()){
            if (acterClass.isInstance(acterWithInitiative.getActer())){
                race.add(acterWithInitiative.getActer());
            }
        }
        return race;
    }

    private void runRound() {
        battle();
        retreat();
        cleanUpBattlefield();
        stateAtTheEndOfTheRound();
    }

    private void battle() {
        for (ActerWithInitiative acter : acters.getArray()) {
            if (!removedActers.contains(acter.getActer())) {
                fight(acter.getActer());
            }
        }
    }

    private void fight(Acter attacker) {

        if (!isActerAttacking()) {
            Command skipRound = commandFactory.createSkipRound(attacker);
            dispatcher.setCommand(skipRound);
            return;
        }

        List<Acter> defenders = createListOfDefenders(attacker);

        if (defenders.isEmpty()) {
            System.out.println(attacker.getName() + " had no one to attack.");
            return;
        }

        attack(attacker, defenders);
    }

    private boolean isActerAttacking() {
        return (random.nextInt(0, 4) > 0);
    }

    private List<Acter> createListOfDefenders(Acter attacker) {
        return Arrays.stream(acters.getArray())
                .map(ActerWithInitiative::getActer)
                .filter(a -> !(a.getClass() == attacker.getClass()) && !(removedActers.contains(a)))
                .collect(Collectors.toList());
    }

    private void attack(Acter attacker, List<Acter> defenders) {
        Acter defender = defenders.get(random.nextInt(0, defenders.size()));
        Command attack = commandFactory.createAttack(attacker, defender);
        dispatcher.setCommand(attack);

        if (defender.getHealthPoints() <= 0) {
            killDefender(defender);
        }
    }

    private void killDefender(Acter defender) {
        removedActers.add(defender);
        System.out.println(defender.getName() + " has died.");
    }

    private void retreat() {
        for (ActerWithInitiative acter : acters.getArray()) {
            if (acter.getActer().getHealthPoints() < 2 && !removedActers.contains(acter.getActer())) {
                Command runAway = commandFactory.createRunAway(acter.getActer());
                dispatcher.setCommand(runAway);
                removedActers.add(acter.getActer());
            }
        }
    }

    private void cleanUpBattlefield() {
        for (Acter acter : removedActers) {
            acters.remove(acter);
        }
    }

    private void stateAtTheEndOfTheRound() {
        System.out.println("End of round " + (currentRound + 1) + ". \n" +
                "Heroes - Trolls - Animals \n" +
                getRace(Hero.class).size() + "\t\t" + getRace(Troll.class).size() + "\t\t" + getRace(Animal.class).size());
    }

    private void outcome() {
        if (getRace(Hero.class).isEmpty()) {
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
        private SortedActersList acters;
        private int totalRounds;

        GameBuilder(int rounds) {
            this.totalRounds = rounds;
            acters = new SortedActersList();
        }

        public GameBuilder addRace(List<Acter> acters) {
            for (Acter acter : acters) {
                this.acters.addActer(new ActerWithInitiative(acter));
            }
            return this;
        }

        public GameBuilder addActers(ActersRepository actersRepository){
            for (ActerWithInitiative acter : actersRepository.getSortedActers().getArray()){
                acters.addActer(acter);
            }
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }
}

package game;

import acters.Acter;
import acters.acters_repository.ActerWithInitiative;
import acters.acters_repository.IActersRepository;
import acters.acters_repository.SortedActersList;
import acters.enemy.Animal;
import acters.enemy.Troll;
import acters.hero.Hero;
import commands.*;

import java.io.Serializable;
import java.util.*;

public class Game implements Serializable {

    private SortedActersList acters;

    private ArrayList<Acter> removedActers = new ArrayList<>();
    private int currentRound;
    private int totalRounds;
    private CommandDispatcher dispatcher;
    private Game(GameBuilder builder) {
        this.acters = builder.acters;
        totalRounds = builder.totalRounds;
        dispatcher = builder.commandDispatcher;
    }

    public void runGame() {
        printCharacterInitiatives();
        runUntilDone(totalRounds);
    }

    void printCharacterInitiatives() {
        for (ActerWithInitiative acterWithInitiative : acters.getArray()) {
            System.out.println(acterWithInitiative.getActer().getName() + " has initiative: "
                    + acterWithInitiative.getInitiative());
        }
    }

    void runUntilDone(int rounds) {
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

    boolean gameDone() {
        return Arrays.stream(acters.getArray())
                .filter(acterWithInitiative -> acterWithInitiative.getActer().isMain())
                .map(a -> a.getActer().getClass())
                .distinct()
                .count() < 2;
    }

    void runRound() {
        Round round = new Round(acters, removedActers, dispatcher);
        round.runRound();
        stateAtTheEndOfTheRound();
    }

    void stateAtTheEndOfTheRound() {
        System.out.println("End of round " + (currentRound + 1) + ". \n" +
                "Heroes - Trolls - Animals \n" +
                getRaceSize(Hero.class) + "\t\t" + getRaceSize(Troll.class) + "\t\t" + getRaceSize(Animal.class));
    }

    int getRaceSize(Class<? extends Acter> acterClass) {
        return (int) acters.stream()
                .map(ActerWithInitiative::getActer)
                .filter(acterClass::isInstance)
                .count();
    }

    void outcome() {
        if (getRaceSize(Hero.class) == 0) {
            System.out.println("Heroes lost!");
        } else {
            System.out.println("Heroes were victorious");
        }
    }

    public SortedActersList getActers() {
        return acters;
    }


    static class GameBuilder {

        private SortedActersList acters;
        private int totalRounds;
        private CommandDispatcher commandDispatcher;

        GameBuilder(int rounds) {
            this.totalRounds = rounds;
            acters = new SortedActersList();
        }

        GameBuilder addRace(List<Acter> acters) {
            for (Acter acter : acters) {
                this.acters.addActer(new ActerWithInitiative(acter));
            }
            return this;
        }

        GameBuilder addActers(IActersRepository actersRepository) {
            for (ActerWithInitiative acter : actersRepository.getSortedActers().getArray()) {
                acters.addActer(acter);
            }
            return this;
        }

        Game build() {
            return new Game(this);
        }

        GameBuilder addCommandDispatcher(CommandDispatcher commandDispatcher) {
            this.commandDispatcher = commandDispatcher;
            return this;
        }
    }
}

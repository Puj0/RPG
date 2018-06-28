package game;

import acters.Acter;
import acters.acters_repository.ActerWithInitiative;
import acters.acters_repository.IActersRepository;
import acters.acters_repository.SortedActersList;
import acters.enemy.Animal;
import acters.enemy.Troll;
import acters.hero.Hero;
import commands.*;
import database.ConnectionRPG;

import java.io.Serializable;
import java.util.*;

public class Game implements Serializable {

    private final String HEROES_LOST = "Heroes lost!";
    private final String HEROES_WON = "Heroes were victorious";
    private final String TIMES_UP = "Time's up!";

    private SortedActersList acters;

    private ArrayList<Acter> removedActers = new ArrayList<>();
    private int currentRound;
    private int totalRounds;
    private CommandDispatcher dispatcher;
    private Printer printer;
    private IRandom random = new ThreadRandom();

    private Game(GameBuilder builder) {
        this.acters = builder.acters;
        totalRounds = builder.totalRounds;
        dispatcher = builder.commandDispatcher;
        printer = builder.printer;
    }

    public void runGame() {
        printCharacterInitiatives();
        runUntilDone(totalRounds);
    }

    private void printCharacterInitiatives() {
        for (ActerWithInitiative acterWithInitiative : acters.getArray()) {
            printer.println(acterWithInitiative.getActer().getName() + " has initiative: "
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
            if (currentRound == 0)
                currentRound++;
            printer.println("It took " + (currentRound - 1) + " rounds.");
        } else {
            printer.println(TIMES_UP);
            addResultToDatabase(TIMES_UP);
        }
    }

    private boolean gameDone() {
        return Arrays.stream(acters.getArray())
                .filter(acterWithInitiative -> acterWithInitiative.getActer().isMain())
                .map(a -> a.getActer().getClass())
                .distinct()
                .count() < 2;
    }

    private void runRound() {
        Round round = new Round(acters, removedActers, dispatcher, random);
        round.runRound();
        stateAtTheEndOfTheRound();
    }

    private void stateAtTheEndOfTheRound() {
        printer.println("End of round " + (currentRound + 1) + ". \n" +
                "Heroes - Trolls - Animals \n" +
                getRaceSize(Hero.class) + "\t\t" + getRaceSize(Troll.class) + "\t\t" + getRaceSize(Animal.class));
    }

    private int getRaceSize(Class<? extends Acter> acterClass) {
        return (int) acters.stream()
                .map(ActerWithInitiative::getActer)
                .filter(acterClass::isInstance)
                .count();
    }

    private void outcome() {
        if (getRaceSize(Hero.class) == 0) {
            printer.println(HEROES_LOST);
            addResultToDatabase(HEROES_LOST);
        } else {
            printer.println(HEROES_WON);
            addResultToDatabase(HEROES_WON);
        }
    }

    private void addResultToDatabase(String gameResult) {
        try {
            String columns = "rounds, result";
            gameResult = gameResult.replace("\'", "\'\'");
            String values = "\'" + totalRounds + "\',\'" + gameResult + "\'";
            String command = "INSERT INTO RPG (" + columns + ") VALUES(" + values + ")";
            ConnectionRPG.getInstance().openConnection();
            ConnectionRPG.getInstance().applyCommand(command);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionRPG.getInstance().closeConnection();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static class GameBuilder {

        private SortedActersList acters;
        private int totalRounds;
        private CommandDispatcher commandDispatcher;
        private Printer printer;
        private IRandom random = new ThreadRandom();

        public GameBuilder(int rounds) {
            this.totalRounds = rounds;
            acters = new SortedActersList();
        }

        public GameBuilder addRace(List<Acter> acters) {
            for (Acter acter : acters) {
                this.acters.addActer(new ActerWithInitiative(acter, random));
            }
            return this;
        }

        public GameBuilder addPrinter(Printer printer) {
            this.printer = printer;
            return this;
        }

        public GameBuilder addActers(IActersRepository actersRepository) {
            for (ActerWithInitiative acter : actersRepository.getSortedActers().getArray())
                acters.addActer(acter);
            return this;
        }

        public GameBuilder addCommandDispatcher(CommandDispatcher commandDispatcher) {
            this.commandDispatcher = commandDispatcher;
            return this;
        }

        public Game build() {
            if (acters == null || printer == null || commandDispatcher == null) {
                try {
                    throw new Exception("Acters, printer and/or dispatcher is null");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return new Game(this);
        }
    }
}

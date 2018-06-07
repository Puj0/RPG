package game;

import acters.Acter;
import acters.acters_repository.ActerWithInitiative;
import acters.acters_repository.ActersRepository;
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
    private PajinaStamparija pajinaStamparija;
    private IRandom random = new ThreadRandom();

    private Game(GameBuilder builder) {
        this.acters = builder.acters;
        totalRounds = builder.totalRounds;
        dispatcher = builder.commandDispatcher;
        pajinaStamparija = builder.pajinaStamparija;
    }

    public void runGame() {
//        printCharacterInitiatives();
        runUntilDone(totalRounds);
    }

    private static boolean addActersFromDatabase() {
        System.out.println("Do you wish to load acters from database (y/n)?");
        Scanner scanner = new Scanner(System.in);
        String yORn = scanner.next();
        return (yORn.toLowerCase().equals("y"));
    }

    private static boolean addActersToDatabase() {
        System.out.println("Do you wish to add acters to the database (y/n)?");
        Scanner scanner = new Scanner(System.in);
        String yORn = scanner.next();
        return (yORn.toLowerCase().equals("y"));
    }

    private void printCharacterInitiatives() {
        for (ActerWithInitiative acterWithInitiative : acters.getArray()) {
            pajinaStamparija.println(acterWithInitiative.getActer().getName() + " has initiative: "
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
            pajinaStamparija.println("It took " + (currentRound - 1) + " rounds.");
        } else {
            pajinaStamparija.println(TIMES_UP);
            try {
                ConnectionRPG.getInstance().insertValueToRPGTable(totalRounds, HEROES_LOST);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        pajinaStamparija.println("End of round " + (currentRound + 1) + ". \n" +
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
            pajinaStamparija.println(HEROES_LOST);
            try {
                ConnectionRPG.getInstance().insertValueToRPGTable(totalRounds, HEROES_LOST);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pajinaStamparija.println(HEROES_WON);
            try {
                ConnectionRPG.getInstance().insertValueToRPGTable(totalRounds, HEROES_WON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class GameBuilder {

        private SortedActersList acters;
        private int totalRounds;
        private CommandDispatcher commandDispatcher;
        private PajinaStamparija pajinaStamparija;
        private IRandom random = new ThreadRandom();

        GameBuilder(int rounds) {
            this.totalRounds = rounds;
            acters = new SortedActersList();
        }

        GameBuilder addRace(List<Acter> acters) {
            for (Acter acter : acters) {
                this.acters.addActer(new ActerWithInitiative(acter, random));
            }
            return this;
        }

        GameBuilder addStamparija(PajinaStamparija pajinaStamparija){
            this.pajinaStamparija = pajinaStamparija;
            return this;
        }

        GameBuilder addActers(IActersRepository actersRepository) {
            if (addActersToDatabase())
                actersRepository.addActersToDatabase();

            if (addActersFromDatabase()) {
                acters = actersRepository.getActersFromDatabase();
            } else {
                for (ActerWithInitiative acter : actersRepository.getSortedActers().getArray())
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

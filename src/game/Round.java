package game;

import acters.Acter;
import acters.acters_repository.ActerWithInitiative;
import acters.acters_repository.SortedActersList;
import commands.CommandAbstractFactory;
import commands.CommandDispatcher;
import commands.CommandFactory;
import commands.concrete_commands.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Round {

    private SortedActersList acters;
    private ArrayList<Acter> removedActers;
    private CommandDispatcher dispatcher;
    private CommandAbstractFactory commandFactory = new CommandFactory();
    private ThreadLocalRandom random = ThreadLocalRandom.current();

    Round(SortedActersList acters, ArrayList<Acter> removedActers, CommandDispatcher dispatcher) {
        this.acters = acters;
        this.removedActers = removedActers;
        this.dispatcher = dispatcher;
    }

    void runRound(){
        battle();
        retreat();
        cleanUpBattlefield();
    }

    void battle() {
        acters.stream()
                .map(ActerWithInitiative::getActer)
                .filter(acter -> !removedActers.contains(acter))
                .forEach(this::fight);
    }

    void fight(Acter attacker) {

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

    boolean isActerAttacking() {
        return (random.nextInt(0, 4) > 0);
    }

    List<Acter> createListOfDefenders(Acter attacker) {
        return acters.stream()
                .map(ActerWithInitiative::getActer)
                .filter(a -> !(a.getClass() == attacker.getClass()) && !(removedActers.contains(a)))
                .collect(Collectors.toList());
    }

    void attack(Acter attacker, List<Acter> defenders) {
        Acter defender = defenders.get(random.nextInt(0, defenders.size()));
        Command attack = commandFactory.createAttack(attacker, defender);
        dispatcher.setCommand(attack);

        if (defender.getHealthPoints() <= 0) {
            killDefender(defender);
        }
    }

    void killDefender(Acter defender) {
        removedActers.add(defender);
        System.out.println(defender.getName() + " has died.");
    }

    void retreat() {
        acters.stream()
                .map(ActerWithInitiative::getActer)
                .filter(acter -> acter.getHealthPoints() < 2 && !removedActers.contains(acter))
                .forEach(acter -> {
                    Command runAway = commandFactory.createRunAway(acter);
                    dispatcher.setCommand(runAway);
                    removedActers.add(acter);
                });
    }

    void cleanUpBattlefield() {
        removedActers.forEach(acters::remove);
    }

    SortedActersList getActers() {
        return acters;
    }

    ArrayList<Acter> getRemovedActers() {
        return removedActers;
    }

    CommandDispatcher getDispatcher() {
        return dispatcher;
    }
}

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
import java.util.stream.Collectors;

class Round {

    private SortedActersList acters;
    private ArrayList<Acter> removedActers;
    private CommandDispatcher dispatcher;
    private CommandAbstractFactory commandFactory = new CommandFactory();
    private IRandom random;
    private PajinaStamparija pajinaStamparija = PajinaStamparija.getInstance();


    Round(SortedActersList acters, ArrayList<Acter> removedActers, CommandDispatcher dispatcher,IRandom random) {
        this.acters = acters;
        this.removedActers = removedActers;
        this.dispatcher = dispatcher;
        this.random = random;
    }

    void runRound(){
        battle();
        retreat();
        cleanUpBattlefield();
    }

    private void battle() {
        acters.stream()
                .map(ActerWithInitiative::getActer)
                .filter(acter -> !removedActers.contains(acter))
                .forEach(this::fight);
    }

    private void fight(Acter attacker) {
        if (!isActerAttacking()) {
            Command skipRound = commandFactory.createSkipRound(attacker);
            dispatcher.setCommand(skipRound);
            return;
        }

        List<Acter> defenders = createListOfDefenders(attacker);

        if (defenders.isEmpty()) {
            pajinaStamparija.println(attacker.getName() + " had no one to attack.");
            return;
        }

        attack(attacker, defenders);
    }

    private boolean isActerAttacking() {
        return (random.nextInt(0, 4) < 3);
    }

    private List<Acter> createListOfDefenders(Acter attacker) {
        return acters.stream()
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
        pajinaStamparija.println(defender.getName() + " has died.");
    }

    private void retreat() {
        acters.stream()
                .map(ActerWithInitiative::getActer)
                .filter(acter -> acter.getHealthPoints() < 2 && !removedActers.contains(acter))
                .forEach(acter -> {
                    Command runAway = commandFactory.createRunAway(acter);
                    dispatcher.setCommand(runAway);
                    removedActers.add(acter);
                });
    }

    private void cleanUpBattlefield() {
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

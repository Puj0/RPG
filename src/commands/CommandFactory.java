package commands;

import acters.Acter;
import commands.concrete_commands.Attack;
import commands.concrete_commands.RunAway;
import commands.concrete_commands.SkipRound;

public class CommandFactory implements CommandAbstractFactory{

    @Override
    public Attack createAttack(Acter attacker, Acter defender) {
        return new Attack(attacker, defender);
    }

    @Override
    public RunAway createRunAway(Acter acter) {
        return new RunAway(acter);
    }

    @Override
    public SkipRound createSkipRound(Acter acter) {
        return new SkipRound(acter);
    }
}

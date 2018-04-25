package commands;

import acters.Acter;
import commands.concrete_commands.Attack;
import commands.concrete_commands.RunAway;
import commands.concrete_commands.SkipRound;

public interface CommandAbstractFactory {

    Attack createAttack(Acter attacker, Acter defender);

    RunAway createRunAway(Acter acter);

    SkipRound createSkipRound(Acter acter);

}

package commands;

import acters.Acter;

public interface CommandAbstractFactory {

    Attack createAttack(Acter attacker, Acter defender);

    RunAway createRunAway(Acter acter);

    SkipRound createSkipRound(Acter acter);

}

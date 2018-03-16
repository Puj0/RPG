package actions;

import acters.Acter;

public class SkipRound implements Action {

    public void execute(Acter acter) {
        System.out.println(acter.getName() + " decided to skip round.");
    }
}

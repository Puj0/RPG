package actions;

import acters.Acter;

public class SkipRound implements Action {

    public SkipRound(Acter acter){
        execute(acter);
    }

    private void execute(Acter acter) {
        System.out.println(acter.getName() + " decided to skip round.");
    }
}

package commands.concrete_commands;

import acters.Acter;

public class SkipRound implements Command {

    private Acter acter;

    public SkipRound(Acter acter){
        this.acter = acter;
    }

    public void execute() {
        System.out.println(acter.getName() + " decided to skip round.");
    }
}

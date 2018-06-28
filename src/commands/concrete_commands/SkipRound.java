package commands.concrete_commands;

import acters.Acter;
import game.Printer;

public class SkipRound implements Command {

    private Acter acter;
    private Printer printer = Printer.getInstance();

    public SkipRound(Acter acter){
        this.acter = acter;
    }

    public void execute() {
        printer.println(acter.getName() + " decided to skip round.");
    }
}

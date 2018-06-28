package commands.concrete_commands;

import acters.Acter;
import game.Printer;

public class RunAway implements Command {

    private Acter acter;
    private Printer printer = Printer.getInstance();

    public RunAway(Acter acter){
        this.acter = acter;
    }

	public void execute() {
        printer.println(acter.getName() + " has left the battle. Such a coward.");
	}
	
}

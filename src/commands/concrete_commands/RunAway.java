package commands.concrete_commands;

import acters.Acter;

public class RunAway implements Command {

    private Acter acter;

    public RunAway(Acter acter){
        this.acter = acter;
    }

	public void execute() {
        System.out.println(acter.getName() + " has left the battle. Such a coward.");
	}
	
}

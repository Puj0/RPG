package commands.concrete_commands;

import acters.Acter;
import game.PajinaStamparija;

public class RunAway implements Command {

    private Acter acter;
    private PajinaStamparija pajinaStamparija = PajinaStamparija.getInstance();

    public RunAway(Acter acter){
        this.acter = acter;
    }

	public void execute() {
        pajinaStamparija.println(acter.getName() + " has left the battle. Such a coward.");
	}
	
}

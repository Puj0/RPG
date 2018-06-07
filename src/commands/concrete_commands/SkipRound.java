package commands.concrete_commands;

import acters.Acter;
import game.PajinaStamparija;

public class SkipRound implements Command {

    private Acter acter;
    private PajinaStamparija pajinaStamparija = PajinaStamparija.getInstance();

    public SkipRound(Acter acter){
        this.acter = acter;
    }

    public void execute() {
        pajinaStamparija.println(acter.getName() + " decided to skip round.");
    }
}

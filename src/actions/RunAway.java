package actions;

import acters.Acter;

public class RunAway implements Action {

    public RunAway(Acter acter){
        execute(acter);
    }

	private void execute(Acter acter) {
        System.out.println(acter.getName() + " has left the battle. Such a coward.");
	}
	
}

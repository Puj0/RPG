package commands.concrete_commands;

import acters.Acter;
import acters.enemy.Enemy;
import game.Printer;

public class Attack implements Command {
	private Acter attacker;
	private Acter defender;
	private Printer printer = Printer.getInstance();


	public Attack(Acter attacker, Acter defender) {
		this.attacker = attacker;
		this.defender = defender;
	}

	public void execute() {

	    if (attacker instanceof Enemy){
	        if(((Enemy) attacker).isAggressive()){
                doDamage();
            }
        } else {
            doDamage();
        }
	}

	private void doDamage(){
		int damage = Math.max(0, attacker.getAttack() - defender.getDefence());
		defender.takeDamage(damage);
		printer.println(attacker.getName() + " attacked " + defender.getName() + ".");
	}

}

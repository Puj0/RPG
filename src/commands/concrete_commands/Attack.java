package commands.concrete_commands;

import acters.Acter;
import acters.enemy.Enemy;

public class Attack implements Command {
	private Acter attacker;
	private Acter defender;


	public Attack(Acter attacker, Acter defender) {
		this.attacker = attacker;
		this.defender = defender;
	}

	public void execute() {
	    if (attacker instanceof Enemy){
	        if(((Enemy) attacker).isIs_aggressive()){
                int damage = Math.max(0, attacker.getAttack() - defender.getDefence());
                defender.takeDamage(damage);
                System.out.println(attacker.getName() + " attacked " + defender.getName() + ".");
            }
        } else {
            int damage = Math.max(0, attacker.getAttack() - defender.getDefence());
            defender.takeDamage(damage);
            System.out.println(attacker.getName() + " attacked " + defender.getName() + ".");
        }
	}

}

package actions;

import acters.Acter;

public class Attack implements Action {
	
	private int damage;

	public Attack(Acter attacker, Acter defender) {
		execute(attacker, defender);
	}

	private void execute(Acter attacker, Acter defender) {
		damage = Math.max(0, attacker.getAttack() - defender.getDefence());
		defender.takeDamage(damage);
		System.out.println(attacker.getName() + " attacked " + defender.getName() + ".");
	}

}

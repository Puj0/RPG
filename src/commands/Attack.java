package commands;

import acters.Acter;

public class Attack implements Command {
	private Acter attacker;
	private Acter defender;

	public Attack(Acter attacker, Acter defender) {
		this.attacker = attacker;
		this.defender = defender;
	}

	public void execute() {
		int damage = Math.max(0, attacker.getAttack() - defender.getDefence());
		defender.takeDamage(damage);
		System.out.println(attacker.getName() + " attacked " + defender.getName() + ".");
		System.out.println(defender.getName() + " now has " + defender.getHealthPoints() + "hp.");
	}

}

package acters.enemy;

import acters.Acter;

public class Troll extends Enemy implements iTroll{

	public Troll(String name, int healthPoints, int attack, int defence) {
		super(name, healthPoints, attack, defence, true);
		System.out.println(name + ", " + healthPoints + "hp, " + attack + "att, " + defence + "def.");
	}
	
	@Override
	public void defend(int damage) {
		if (this.getHealthPoints() > 5) damage *= 0.7;
		this.setHealthPoints(getHealthPoints() - damage);
	}
	
	
	
	public void attack(Acter acter) {
		int damage = Math.max(0, this.getAttack() - acter.getDefence());
		acter.defend(damage);
	}
	
}

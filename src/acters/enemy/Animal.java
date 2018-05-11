package acters.enemy;

public class Animal extends Enemy implements IAnimal {

	public Animal(String name, int healthPoints, int attack, int defence, int initiative) {
		super(name, healthPoints, attack, defence, initiative, false);
		System.out.println(name + ", " + healthPoints + "hp, " + attack + "att, "
				+ defence + "def, " + initiative + "init.");
	}

	@Override
	public void defend(int damage) {
		if (this.getHealthPoints() < 10) damage *= 1.15;
		else if (this.getHealthPoints() > 25) damage *= 0.8;
		this.takeDamage(damage);
		setAggressive(true);
	}

	@Override
	public void replenishHealth() {
	}

	@Override
	public boolean isMain() {
		return false;
	}
}

package acters.enemy;

public class Troll extends Enemy implements ITroll {

	public Troll(String name, int healthPoints, int attack, int defence, int initiative) {
		super(name, healthPoints, attack, defence, initiative,true);
		System.out.println(name + ", " + healthPoints + "hp, " + attack + "att, " + defence
				+ "def, " + initiative + "init.");
	}
	
	@Override
	public void defend(int damage) {
		if (damage < 0) throw new IllegalArgumentException("Damage cannot be negative");
		if (damage > 0) {
			if (this.getHealthPoints() > 5) damage *= 0.7;
			this.takeDamage(damage);
		}
	}

    @Override
    public void replenishHealth() {
        setHealthPoints(getHealthPoints() + 2);
    }


	@Override
	public boolean isMain() {
		return true;
	}
}

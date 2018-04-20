package acters.enemy;

public class Troll extends Enemy implements ITroll {

	public Troll(String name, int healthPoints, int attack, int defence, int initiative) {
		super(name, healthPoints, attack, defence, initiative,true);
		System.out.println(name + ", " + healthPoints + "hp, " + attack + "att, " + defence
				+ "def, " + initiative + "init.");
	}
	
	@Override
	public void defend(int damage) {
		if (this.getHealthPoints() > 5) damage *= 0.7;
		this.takeDamage(getHealthPoints() - damage);
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

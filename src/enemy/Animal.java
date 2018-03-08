
public class Animal extends Enemy{

	public Animal(String name, int healthPoints, int attack, int defence) {
		super(name, healthPoints, attack, defence, false);
		System.out.println(name + ", " + healthPoints + "hp, " + attack + "att, " + defence + "def.");
	}
	
	@Override
	public void defend(int damage) {
		if (this.getHealthPoints() < 10) damage *= 1.15;
		else if (this.getHealthPoints() > 25) damage *= 0.8;
		this.setHealthPoints(getHealthPoints() - damage);
	}

}

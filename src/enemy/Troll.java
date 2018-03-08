
public class Troll extends Enemy {

	public Troll(String name, int healthPoints, int attack, int defence) {
		super(name, healthPoints, attack, defence, true);
		System.out.println(name + ", " + healthPoints + "hp, " + attack + "att, " + defence + "def.");
	}

	@Override
	public void defend(int damage) {
		if (this.getHealthPoints() > 5) damage *= 0.7;
		this.setHealthPoints(getHealthPoints() - damage);
	}
	
	
	
	public void attack(Hero hero) {
		int damage = Math.max(0, this.getAttack() - hero.getDefence());
		hero.defend(damage);
	}
	
	public void attack(Animal animal) {
		int damage = Math.max(0, this.getAttack() - animal.getDefence());
		animal.defend(damage);
	}
	
}

package acters.enemy;

public abstract class Enemy implements IEnemy {
	private String name;
	private int healthPoints;
	private int attack;
	private int defence;
	private int initiative;
	private boolean is_aggressive;
	
	Enemy(String name, int healthPoints, int attack, int defence,
				 int initiative, boolean is_aggressive) {
		super();
		this.name = name;
		this.healthPoints = healthPoints;
		this.attack = attack;
		this.defence = defence;
		this.initiative = initiative;
		this.is_aggressive = is_aggressive;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getHealthPoints() {
		return healthPoints;
	}

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void takeDamage(int damage) {
		this.healthPoints -= damage;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public boolean isIs_aggressive() {
		return is_aggressive;
	}

	public void setIs_aggressive(boolean is_aggressive) {
		this.is_aggressive = is_aggressive;
	}

	public void defend(int damage) {
		healthPoints -= damage;
	}

	public int getInitiative(){
		return initiative;
	}

}

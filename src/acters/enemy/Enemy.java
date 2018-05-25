package acters.enemy;

public abstract class Enemy implements IEnemy {
	private String name;
	private int healthPoints;
	private int attack;
	private int defence;
	private int initiative;
	private boolean aggressive;
	
	Enemy(String name, int healthPoints, int attack, int defence,
				 int initiative, boolean aggressive) {
		if (name == null){
			throw new IllegalArgumentException("illegal name: [null]");
		}

		if (healthPoints < 0){
			throw new IllegalArgumentException("illegal health: [" + healthPoints + "]");
		}
		if (attack < 0){
			throw new IllegalArgumentException("illegal attack: [" + healthPoints + "]");
		}
		if (defence < 0){
			throw new IllegalArgumentException("illegal health: [" + defence + "]");
		}
		if (initiative < 0){
			throw new IllegalArgumentException("illegal attack: [" + initiative + "]");
		}
		this.name = name;
		this.healthPoints = healthPoints;
		this.attack = attack;
		this.defence = defence;
		this.initiative = initiative;
		this.aggressive = aggressive;
	}

	public String getName() {
		return name;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void takeDamage(int damage) {
		this.healthPoints -= damage;
		if(!aggressive){
			setAggressive(true);
		}
	}

	public int getAttack() {
		return attack;
	}

	public int getDefence() {
		return defence;
	}

	public boolean isAggressive() {
		return aggressive;
	}

	public void setAggressive(boolean aggressive) {
		this.aggressive = aggressive;
	}

	public void defend(int damage) {
		healthPoints -= damage;
	}

	public int getInitiative(){
		return initiative;
	}

}

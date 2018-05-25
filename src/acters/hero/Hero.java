package acters.hero;

public class Hero implements IHero {
	private String name;
	private RoleClass role;
	private int healthPoints;
	private int attack;
	private int defence;
	private int initiative;
	
	public Hero(String name, RoleClass role, int healthPoints, int attack, int defence,
				int initiative) {
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
		this.role = role;
		this.healthPoints = healthPoints;
		this.attack = attack;
		this.defence = defence;
		this.initiative = initiative;
		System.out.println(name + ", " + role.toString() + ", " + healthPoints + "hp, "
				+ attack + "att, " + defence + "def, " + initiative + "init.");
	}

	public String getName() {
		return name;
	}

	public RoleClass getRoleClass() {
		return role;
	}
	
	public void setRoleClass(RoleClass role) {
		this.role = role;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void takeDamage(int damage) {
		healthPoints -= damage;
	}

	public void replenishHealth(){
		healthPoints += 3;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefence() {
		return defence;
	}

	@Override
	public int getInitiative() {
		return initiative;
	}

	@Override
	public boolean isMain() {
		return true;
	}
}

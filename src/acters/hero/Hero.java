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
		super();
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
		healthPoints = getHealthPoints() - damage;
	}

	public void replenishHealth(){
		this.healthPoints = getHealthPoints() + 3;
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


}

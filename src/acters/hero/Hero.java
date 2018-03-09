package acters.hero;
import java.lang.Math;

import acters.enemy.Enemy;
import actions.Action;

public class Hero implements iHero{
	private String name;
	private Role role;
	private int healthPoints;
	private int attack;
	private int defence;
	
	public Hero(String name, Role role, int healthPoints, int attack, int defence) {
		super();
		this.name = name;
		this.role = role;
		this.healthPoints = healthPoints;
		this.attack = attack;
		this.defence = defence;
		System.out.println(name + ", " + role.toString() + ", " + healthPoints + "hp, " + attack + "att, " + defence + "def.");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
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

	
	public void defend(int damage) {
		this.setHealthPoints(getHealthPoints() - damage);
	}

	@Override
	public void doAction(Action action) {
		// TODO Auto-generated method stub
	}

}

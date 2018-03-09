package acters;

import actions.Action;

import java.io.Serializable;

public interface Acter extends Serializable{
	String getName();
	
	void setName(String name);
	
	int getHealthPoints();
	
	void setHealthPoints(int healthPoints);
	
	int getAttack();
	
	void setAttack(int attack);
	
	int getDefence();
	
	void setDefence(int defence);

	void doAction(Action action);

	void defend(int damage);
}

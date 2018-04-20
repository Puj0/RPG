package acters;

import java.io.Serializable;

public interface Acter extends Serializable{

	String getName();

	int getHealthPoints();
	
	void takeDamage(int damage);

	void replenishHealth();
	
	int getAttack();

	int getDefence();

	int getInitiative();

	boolean isMain();

}

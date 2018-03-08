package acters;

public interface Acter {
	String getName();
	
	void setName(String name);
	
	/*Role getRole();
	
	void setRole(Role role);*/
	
	int getHealthPoints();
	
	void setHealthPoints(int healthPoints);
	
	int getAttack();
	
	void setAttack(int attack);
	
	int getDefence();
	
	void setDefence(int defence);
	
//	void attack(Enemy enemy);
	
	void defend(int damage);
}

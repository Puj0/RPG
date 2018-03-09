package acters.hero;

import acters.Acter;
import acters.enemy.Enemy;

public interface iHero extends Acter {

	public Role getRole();
	
	public void setRole(Role role);

}

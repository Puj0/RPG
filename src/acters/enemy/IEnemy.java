package acters.enemy;

import acters.Acter;

public interface IEnemy extends Acter {
	public boolean isAggressive();

	public void setAggressive(boolean aggressive);
}

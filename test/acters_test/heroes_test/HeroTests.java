package acters_test.heroes_test;

import acters.hero.Hero;
import acters.hero.RoleClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HeroTests {

    Hero hero;
    private String name;
    private RoleClass role;
    private int healthPoints;
    private int attack;
    private int defence;
    private int initiative;

    @Before
    public void setUp(){
        hero = new Hero("Hero 1", RoleClass.BARBARIAN, 20, 2, 3, 5);
    }

    @Test
    public void takeDamage_shouldReturn14_givenDamageIs6AndHas20HealthPoints() {
        hero.takeDamage(6);
        assertEquals(14, hero.getHealthPoints(),0);
    }

    @Test
    public void replenishHealth_shouldReturn23_givenHealthIs20(){
        hero.replenishHealth();
        assertEquals(23, hero.getHealthPoints());
    }

    @Test
    public void isMain_shouldReturnTrue(){
        assertTrue(hero.isMain());
    }


}

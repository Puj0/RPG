package acters.enemy_test;

import acters.enemy.Troll;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TrollTest {

    private Troll troll;

    @Before
    public void setUp() {
        troll = new Troll("Troll 1", 20, 2, 3, 5);
    }

    @Test
    public void constructor_shouldSetNameHealthAttackDefenceInitiativeAndAggression(){
        Troll troll = new Troll("Troll 1", 20, 2, 3, 5);

        assertEquals(troll.getName(), "Troll 1");
        assertEquals(troll.getHealthPoints(), 20);
        assertEquals(troll.getAttack(), 2);
        assertEquals(troll.getDefence(), 3);
        assertEquals(troll.getInitiative(), 5);
        assertTrue(troll.isAggressive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void defend_shouldThrowException_givenDamageIsNegative(){
        troll.defend(-5);
    }

    @Test
    public void isAggressive_shouldReturnTrue_givenDamageIsNotDealt() {
        assertTrue(troll.isAggressive());
    }

    @Test
    public void isAggressive_shouldReturnTrue_givenDamageIsDealt() {
        troll.defend(0);
        assertTrue(troll.isAggressive());
    }

    @Test
    public void defend_shouldReturn17_givenDamageIs5AndHas20HealthPoints() {
        troll.defend(5);
        assertEquals(17, troll.getHealthPoints(),0);
    }

    @Test
    public void defend_shouldReturn1_givenDamageIs3AndHas4HealthPoints() {
        troll.setHealthPoints(4);
        troll.defend(3);
        assertEquals(1, troll.getHealthPoints(),0);
    }

    @Test
    public void replenishHealth_shouldReturn22_givenHealthIs20(){
        troll.replenishHealth();
        assertEquals(22, troll.getHealthPoints());
    }

    @Test
    public void isMain_shouldReturnTrue(){
        assertTrue(troll.isMain());
    }
}




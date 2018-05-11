package acters_test.enemy_test;

import acters.enemy.Animal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnimalTests {

    private Animal animal;

    @Before
    public void setUp() {
        animal = new Animal("Animal 1", 20, 2, 3, 5);
    }


    @Test
    public void defend_shouldReturn16_givenDamageIs10AndHas30HealthPoints() {
        animal.setHealthPoints(30);
        animal.defend(10);
        assertEquals(22, animal.getHealthPoints(),0);
    }

    @Test
    public void isAggressive_shouldReturnFalse_givenDamageIsNotDealt() {
        assertFalse(animal.isAggressive());
    }

    @Test
    public void isAggressive_shouldReturnTrue_givenDamageIsDealt() {
        animal.defend(0);
        assertTrue(animal.isAggressive());
    }

    @Test
    public void defend_shouldReturn15_givenDamageIs5AndHas20HealthPoints() {
        animal.defend(5);
        assertEquals(15, animal.getHealthPoints(),0);
    }

    @Test
    public void defend_shouldReturn2_givenDamageIs6AndHas8HealthPoints() {
        animal.setHealthPoints(8);
        animal.defend(6);
        assertEquals(2, animal.getHealthPoints(),0);
    }

    @Test
    public void replenishHealth_shouldStaySame(){
        int currentHealth = animal.getHealthPoints();
        animal.replenishHealth();
        assertEquals(currentHealth, animal.getHealthPoints());
    }

    @Test
    public void isMain_shouldReturnFalse(){
        assertFalse(animal.isMain());
    }
}


package acters.enemy_test;

import acters.enemy.Animal;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class AnimalTests {

    private Animal animal;
    private final static String VALID_NAME = "Animal 1";
    private final static int VALID_HEALTH = 20;
    private final static int VALID_ATTACK = 10;
    private final static int VALID_DEFENCE = 5;
    private final static int VALID_INITIATIVE = 2;

    @Before
    public void setUp() {
        animal = new Animal("Animal 1", 20, 2, 3, 5);
    }


    @Test
    public void constructor_shouldSetNameHealthAttackDefenceInitiativeAndAggression(){
        assertEquals(animal.getName(), "Animal 1");
        assertEquals(animal.getHealthPoints(), 20);
        assertEquals(animal.getAttack(), 2);
        assertEquals(animal.getDefence(), 3);
        assertEquals(animal.getInitiative(), 5);
        assertFalse(animal.isAggressive());
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
        assertFalse(animal.isAggressive());
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

    private static Object[] getNegativeNumber(){
        return new Integer[][]{{-10}, {-1}, {-12334}};
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowIAEForInvalidName(){
        new Animal(null, VALID_HEALTH, VALID_ATTACK, VALID_DEFENCE, VALID_INITIATIVE);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getNegativeNumber")
    public void constructorShouldThrowIAEForInvalidHealth(int invalidHealth){
        new Animal(VALID_NAME, invalidHealth, VALID_ATTACK, VALID_DEFENCE, VALID_INITIATIVE);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getNegativeNumber")
    public void constructorShouldThrowIAEForInvalidAttack(int invalidAttack){
        new Animal(VALID_NAME, VALID_HEALTH, invalidAttack, VALID_DEFENCE, VALID_INITIATIVE);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getNegativeNumber")
    public void constructorShouldThrowIAEForInvalidDefence(int invalidDefence){
        new Animal(VALID_NAME, VALID_HEALTH, VALID_ATTACK, invalidDefence, VALID_INITIATIVE);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getNegativeNumber")
    public void constructorShouldThrowIAEForInvalidInitiative(int invalidInitiative){
        new Animal(VALID_NAME, VALID_HEALTH, VALID_ATTACK, VALID_DEFENCE, invalidInitiative);
    }

}


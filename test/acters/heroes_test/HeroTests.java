package acters.heroes_test;

import acters.hero.Hero;
import acters.hero.RoleClass;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class HeroTests {

    private Hero hero;
    private final static String VALID_NAME = "Hero 1";
    private final static RoleClass VALID_ROLE = RoleClass.BARBARIAN;
    private final static RoleClass VALID_ROLE2 = RoleClass.BARD;
    private final static int VALID_HEALTH = 20;
    private final static int VALID_ATTACK = 2;
    private final static int VALID_DEFENCE = 3;
    private final static int VALID_INITIATIVE = 5;


    @Before
    public void setUp(){
        hero = new Hero("Hero 1", RoleClass.BARBARIAN, 20, 2, 3, 5);
    }

    @Test
    public void constructor_shouldSetNameHealthAttackDefenceInitiativeAndAggression(){
        Hero hero = new Hero("Hero 1", RoleClass.BARBARIAN, 20, 2, 3, 5);

        assertEquals(hero.getName(), "Hero 1");
        assertEquals(hero.getRoleClass(), RoleClass.BARBARIAN);
        assertEquals(hero.getHealthPoints(), 20);
        assertEquals(hero.getAttack(), 2);
        assertEquals(hero.getDefence(), 3);
        assertEquals(hero.getInitiative(), 5);
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
    public void setRole_shouldReturnBard_givenBard(){
        hero.setRoleClass(RoleClass.BARD);
        assertEquals(VALID_ROLE2, hero.getRoleClass());
    }

    @Test
    public void isMain_shouldReturnTrue(){
        assertTrue(hero.isMain());
    }

    private static final Object[] getNegativeNumber(){
        return new Integer[][]{{-10}, {-1}, {-333}};
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowIAEForInvalidName(){
        new Hero(null, VALID_ROLE, VALID_HEALTH, VALID_ATTACK, VALID_DEFENCE, VALID_INITIATIVE);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getNegativeNumber")
    public void constructorShouldThrowIAEForInvalidHealth(int invalidHealth){
        new Hero(VALID_NAME, VALID_ROLE, invalidHealth, VALID_ATTACK, VALID_DEFENCE, VALID_INITIATIVE);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getNegativeNumber")
    public void constructorShouldThrowIAEForInvalidAttack(int invalidAttack){
        new Hero(VALID_NAME, VALID_ROLE, VALID_HEALTH, invalidAttack, VALID_DEFENCE, VALID_INITIATIVE);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getNegativeNumber")
    public void constructorShouldThrowIAEForInvalidDefence(int invalidDefence){
        new Hero(VALID_NAME, VALID_ROLE, VALID_HEALTH, VALID_ATTACK, invalidDefence, VALID_INITIATIVE);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getNegativeNumber")
    public void constructorShouldThrowIAEForInvalidInitiative(int invalidInitiative){
        new Hero(VALID_NAME, VALID_ROLE, VALID_HEALTH, VALID_ATTACK, VALID_DEFENCE, invalidInitiative);
    }
}

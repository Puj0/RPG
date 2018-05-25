package commands.concrete_commands;

import acters.enemy.Animal;
import acters.enemy.Troll;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AttackTests {

    private Troll troll = new Troll("Troll 1", 20, 10, 5, 2);
    private Animal animal = new Animal("Animal 1", 20, 7, 5, 2);
    private Attack attackTA, attackAT;

    @Before
    public void setUp(){
        attackTA = new Attack(troll, animal);
        attackAT = new Attack(animal, troll);
    }

    @After
    public void reset(){
        troll.setHealthPoints(20);
        animal.setHealthPoints(20);
    }

    @Test
    public void execute_shouldReturn15_givenAttackerIsTroll(){
        Assert.assertTrue(troll.isAggressive());
        attackTA.execute();
        Assert.assertEquals(15, animal.getHealthPoints());
    }

    @Test
    public void execute_shouldReturn20_givenAttackerIsAnimal(){
        Assert.assertFalse(animal.isAggressive());
        attackAT.execute();
        Assert.assertEquals(20, animal.getHealthPoints());
    }

    @Test
    public void execute_shouldReturn18_givenAttackerIsAnimalWhichWasAttacked(){
        Assert.assertFalse(animal.isAggressive());
        attackTA.execute();
        Assert.assertTrue(animal.isAggressive());
        attackAT.execute();
        Assert.assertEquals(18, troll.getHealthPoints());
    }

}

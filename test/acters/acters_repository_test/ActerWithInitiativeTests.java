package acters.acters_repository_test;

import acters.Acter;
import acters.acters_repository.ActerWithInitiative;
import acters.hero.Hero;
import acters.hero.RoleClass;
import game.IRandom;
import game.Derandomizer;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
public class ActerWithInitiativeTests {

    private Acter acter;
    private ActerWithInitiative acterWithInitiative;
    private IRandom random = new Derandomizer();

    @Before
    public void setUp(){
        acter = new Hero("Hero 1", RoleClass.BARBARIAN, 20, 2, 3, 5);
        acterWithInitiative = new ActerWithInitiative(acter, random);
    }

    private static Object[] repeatTest40Times() {
        return new Object[40][0];
    }

    @Test
    @Parameters(method = "repeatTest40Times")
    public void initiative_shouldBeBetween6And26_givenStartingInitiativeIs5(Object nothing){
        Assert.assertTrue(acterWithInitiative.getInitiative() > 5);
        Assert.assertTrue(acterWithInitiative.getInitiative() < 26);
    }

    @Test
    public void getActer() {
        Assert.assertEquals(acter, acterWithInitiative.getActer());
    }

}

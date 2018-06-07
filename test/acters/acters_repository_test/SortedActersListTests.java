package acters.acters_repository_test;

import acters.acters_repository.ActerWithInitiative;
import acters.acters_repository.SortedActersList;
import acters.hero.Hero;
import acters.hero.RoleClass;
import game.IRandom;
import game.PajinAntiRandom;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class SortedActersListTests {

    private static SortedActersList sortedActersList;
    private static IRandom random = new PajinAntiRandom();

    private static ActerWithInitiative acter1 = new ActerWithInitiative(
            new Hero("Hero 1", RoleClass.BARBARIAN, 12, 5, 4, 2), random);
    private static ActerWithInitiative acter2 = new ActerWithInitiative(
            new Hero("Hero 2", RoleClass.BARBARIAN, 12, 5, 4, 45), random);
    private static ActerWithInitiative acter3 = new ActerWithInitiative(
            new Hero("Hero 3", RoleClass.BARBARIAN, 12, 5, 4, 67), random);
    private static ActerWithInitiative acter4 = new ActerWithInitiative(
            new Hero("Hero 4", RoleClass.BARBARIAN, 12, 5, 4, 24), random);

    private ActerWithInitiative[] actersWithInitiatives;

    public static Collection<Object[]> actersForTests() {
        return Arrays.asList(
            new Object[]{acter1, new ActerWithInitiative[]{acter1}},
            new Object[]{acter2, new ActerWithInitiative[]{acter2, acter1}},
            new Object[]{acter3, new ActerWithInitiative[]{acter3, acter2, acter1}},
            new Object[]{acter4, new ActerWithInitiative[]{acter3, acter2, acter4, acter1}});
        }

    @BeforeClass
    public static void setUp() {
        sortedActersList = new SortedActersList();
    }

    @Test
    @Parameters(method = "actersForTests")
    public void testAssertList(ActerWithInitiative acter, ActerWithInitiative[] actersWithInitiatives) {
        sortedActersList.addActer(acter);
        Assert.assertArrayEquals(sortedActersList.getArray(), actersWithInitiatives);
    }

    @Test
    public void removeActerTest() {
        SortedActersList sortedActersList2 = new SortedActersList();
        sortedActersList2.addActer(acter1);
        sortedActersList2.addActer(acter2);
        sortedActersList2.addActer(acter3);
        sortedActersList2.addActer(acter4);
        sortedActersList2.remove(acter2.getActer());
        Assert.assertArrayEquals(sortedActersList2.getArray(), new ActerWithInitiative[]{acter3, acter4, acter1});
    }
}


package game;

import acters.Acter;
import acters.acters_repository.ActerWithInitiative;
import acters.acters_repository.ActersRepository;
import acters.acters_repository.SortedActersList;
import acters.enemy.Troll;
import acters.hero.Hero;
import acters.hero.RoleClass;
import commands.CommandDispatcher;

import commands.concrete_commands.SkipRound;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class RoundTest {

    private Round round;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    Acter acter1 = new Hero("Hero 1", RoleClass.BARBARIAN, 20, 2, 3, 46);
    Acter acter2 = new Hero("Hero 2", RoleClass.BARBARIAN, 20, 2, 3, 26);
    Acter acter3 = new Hero("Hero 3", RoleClass.BARBARIAN, 20, 2, 3, 25);
    Acter acter4 = new Troll("Troll 1", 20, 2, 3, 3);

    @BeforeClass
    public static void setUpOutputStream() {
        System.setOut(new PrintStream(outContent));
    }

    @Before
    public void setUp(){
        ActersRepository actersRepository = new ActersRepository(0,0);
        SortedActersList sortedActersList = actersRepository.getSortedActers();
        sortedActersList.addActer(new ActerWithInitiative(acter1));
        sortedActersList.addActer(new ActerWithInitiative(acter2));
        sortedActersList.addActer(new ActerWithInitiative(acter3));
        sortedActersList.addActer(new ActerWithInitiative(acter4));

        round = spy(new Round(sortedActersList, new ArrayList<>(), new CommandDispatcher()));
    }

    @Test
    public void constructor_shouldSetActersRemovedActersAndDispatcher(){
        SortedActersList acters = new SortedActersList();
        ArrayList<Acter> removedActers = new ArrayList<>();
        CommandDispatcher dispatcher = new CommandDispatcher();
        Round round = new Round(acters, removedActers, dispatcher);

        Assert.assertEquals(acters, round.getActers());
        Assert.assertEquals(removedActers, round.getRemovedActers());
        Assert.assertEquals(dispatcher, round.getDispatcher());
    }

    @Test
    public void runRound_shouldRunBattleRetreatAndCleanUpBattlefield(){
        round.runRound();
        verify(round).battle();
        verify(round).retreat();
        verify(round).cleanUpBattlefield();
    }

    @Test
    public void battle_shouldInvoke4Fights_givenActersSizeIs4(){
        round.battle();
        ActerWithInitiative[] attackers = round.getActers().getArray();
        verify(round).fight(attackers[0].getActer());
        verify(round).fight(attackers[1].getActer());
        verify(round).fight(attackers[2].getActer());
        verify(round).fight(attackers[3].getActer());
    }

    @Test
    public void createListOfDefenders_shouldReturn3Heroes_givenAttackerIsTroll(){
        Acter attacker = round.getActers().getArray()[3].getActer();
        Assert.assertEquals(3, round.createListOfDefenders(attacker).size());
    }

    @Test
    public void createListOfDefenders_shouldReturn1Troll_givenAttackerIsHero(){
        Acter attacker = round.getActers().getArray()[0].getActer();
        Assert.assertEquals(1, round.createListOfDefenders(attacker).size());
    }

    @Test
    public void attack_shouldKillDefender_givenAttackIs24Defence3AndHealth20(){

        Acter attacker = new Hero("Hero 1", RoleClass.BARBARIAN, 20, 24, 3, 46);
        List<Acter> defender = new ArrayList<>();
        defender.add(acter4);
        round.attack(attacker, defender);
        verify(round).killDefender(acter4);
    }

    @Test
    public void attack_shouldNotKillDefender_givenAttackIs22Defence3AndHealth20(){

        Acter attacker = new Hero("Hero 1", RoleClass.BARBARIAN, 20, 22, 3, 46);
        List<Acter> defender = new ArrayList<>();
        defender.add(acter4);
        round.attack(attacker, defender);
        verify(round, never()).killDefender(acter4);
    }

    @Test
    public void killDefender_shouldRemoveDefender_givenHealthIsLessThan1(){
        int numberOfRemovedBefore = round.getRemovedActers().size();
        round.killDefender(acter4);
        int numberOfRemovedAfter = round.getRemovedActers().size();
        Assert.assertEquals(numberOfRemovedBefore + 1, numberOfRemovedAfter);
    }

    @Test
    public void retreat_shouldRemoveActer_givenDefenderHasHealth1(){
        int numberOfRemovedBefore = round.getRemovedActers().size();
        Acter attacker = new Hero("Hero 1", RoleClass.BARBARIAN, 20, 22, 3, 46);
        List<Acter> defender = new ArrayList<>();
        defender.add(acter4);
        round.attack(attacker, defender);
        Assert.assertEquals(1, acter4.getHealthPoints());
        round.retreat();
        int numberOfRemovedAfter = round.getRemovedActers().size();
        Assert.assertEquals(numberOfRemovedBefore + 1, numberOfRemovedAfter);
    }


    @AfterClass
    public static void tearDown(){
        System.setOut(System.out);
    }


}
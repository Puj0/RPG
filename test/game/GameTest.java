package game;

import acters.Acter;
import acters.acters_repository.ActerWithInitiative;
import acters.acters_repository.ActersRepository;
import acters.acters_repository.SortedActersList;
import acters.enemy.Troll;
import acters.hero.Hero;
import acters.hero.RoleClass;
import commands.CommandDispatcher;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;

import static org.mockito.Mockito.*;

public class GameTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final int ZERO_ROUNDS = 0;
    private ActersRepository actersRepository = new ActersRepository(5, 0);
    private CommandDispatcher commandDispatcher = new CommandDispatcher();
    private Game game, gameSpy;
    private SortedActersList sortedActersList;

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
        game = new Game.GameBuilder(ZERO_ROUNDS)
                .addActers(actersRepository)
                .addCommandDispatcher(commandDispatcher)
                .build();
        gameSpy = spy(game);
    }

    @Test
    public void runGame_shouldRunPrintCharacterInitiativesAndRunUntilDone(){
        gameSpy.runGame();
        verify(gameSpy).printCharacterInitiatives();
        verify(gameSpy).runUntilDone(ZERO_ROUNDS);
    }

    @Test
    public void runUntilDone_shouldEnd_givenRoundsIs0(){
        gameSpy.runUntilDone(ZERO_ROUNDS);
        verify(gameSpy, never()).runRound();
    }

    @Test
    public void gameDone_shouldReturnFalse_givenThereAreOnlyActersOfOneClassLeft(){
        actersRepository.getSortedActers().remove(acter4);
        gameSpy = spy(new Game.GameBuilder(ZERO_ROUNDS)
        .addActers(actersRepository).addCommandDispatcher(commandDispatcher).build());
    }

    @Test
    public void getRaceSize_shouldReturn0_givenThereAreNoActers(){

        sortedActersList = new SortedActersList();
    }

    @Test
    public void stateAtTheEndOfTheRound_shouldPrint0_given0Acters(){

        outContent.reset();
        sortedActersList = gameSpy.getActers();
        for (ActerWithInitiative acterWithInitiative: sortedActersList.getArray()) {
            gameSpy.getActers().remove(acterWithInitiative.getActer());
        }
        gameSpy.stateAtTheEndOfTheRound();

        Assert.assertEquals("End of round 1. \n" + "Heroes - Trolls - Animals \n" + "0\t\t" + "0\t\t0\r\n", outContent.toString());
    }

    @Test
    public void getRaceSize_shouldReturn5_givenThereAre5Heroes(){

    }

    @Test
    public void outcome_shouldPrintHeroesLost_givenThereAreLeftoverHeroes(){

        doReturn(1).when(gameSpy).getRaceSize(Hero.class);
        outContent.reset();
        gameSpy.outcome();
        Assert.assertEquals("Heroes were victorious\r\n", outContent.toString());
    }

    @Test
    public void outcome_shouldPrintHeroesLost_givenThereAreNoHeroes(){

        doReturn(0).when(gameSpy).getRaceSize(Hero.class);
        outContent.reset();
        gameSpy.outcome();
        Assert.assertEquals("Heroes lost!\r\n", outContent.toString());
    }

    @AfterClass
    public static void restoreStream(){
        System.setOut(System.out);
    }

}
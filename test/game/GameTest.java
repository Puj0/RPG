package game;

import acters.Acter;
import acters.acters_repository.ActersRepository;
import acters.enemy.Troll;
import acters.hero.Hero;
import acters.hero.RoleClass;
import commands.CommandDispatcher;
import database.ConnectionRPG;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class GameTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final int ZERO_ROUNDS = 0;
    private ConnectionRPG connectionRPG = mock(ConnectionRPG.class);

    private ActersRepository actersRepository = new ActersRepository(5, 0, connectionRPG);
    private CommandDispatcher commandDispatcher = new CommandDispatcher();
    private Game game;
    private PajinaStamparija pajinaStamparija = spy(PajinaStamparija.class);

    private Acter acter1 = new Hero("Hero 1", RoleClass.BARBARIAN, 20, 2, 3, 46);
    private Acter acter2 = new Hero("Hero 2", RoleClass.BARBARIAN, 20, 2, 3, 26);
    private Acter acter3 = new Hero("Hero 3", RoleClass.BARBARIAN, 20, 2, 3, 25);
    private Acter acter4 = new Troll("Troll 1", 20, 2, 3, 3);

    @BeforeClass
    public static void setUpOutputStream() {
        System.setOut(new PrintStream(outContent));
    }

    @Before
    public void setUp(){
    }

    @Ignore
    @Test
    public void runGame_shouldPrintCharacterInitiativesAndPrintTimesUpWhenAllRoundsHavePassedAndGameIsNotDone(){
        game = new Game.GameBuilder(ZERO_ROUNDS)
                .addActers(actersRepository)
                .addCommandDispatcher(commandDispatcher)
                .addStamparija(pajinaStamparija)
                .build();
        game.runGame();
        verify(pajinaStamparija, times(10)).println(matches(".* has initiative: 2"));
        verify(pajinaStamparija).println("Time's up!");
    }

    @Ignore
    @Test
    public void runGame_shouldPrintCharacterInitiativesAndPrintTimesUpWhenAllRoundsHavePassedAndGameIsDone(){
        game = new Game.GameBuilder(ZERO_ROUNDS)
                .addRace(Arrays.asList(acter1, acter2, acter3))
                .addCommandDispatcher(commandDispatcher)
                .addStamparija(pajinaStamparija)
                .build();
        game.runGame();

        verify(pajinaStamparija).println("Heroes were victorious");
        verify(pajinaStamparija).println("It took 0 rounds.");
    }

    @Ignore
    @Test
    public void runGame_shouldPrintStateAtTheEndOfRound(){
        game = new Game.GameBuilder(1)
                .addRace(Arrays.asList(acter1, acter2, acter3, acter4))
                .addCommandDispatcher(commandDispatcher)
                .addStamparija(pajinaStamparija)
                .build();
        game.runGame();
        verify(pajinaStamparija).println("End of round 1. \n" +
                "Heroes - Trolls - Animals \n" +
                "3\t\t1\t\t0");
    }

    @AfterClass
    public static void restoreStream(){
        System.setOut(System.out);
    }

}
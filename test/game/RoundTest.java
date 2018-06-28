package game;

import acters.Acter;
import acters.acters_repository.ActerWithInitiative;
import acters.acters_repository.ActersRepository;
import acters.acters_repository.SortedActersList;
import acters.enemy.Troll;
import acters.hero.Hero;
import acters.hero.RoleClass;
import commands.CommandDispatcher;

import database.ConnectionRPG;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class RoundTest {

    private Round round;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Derandomizer derandomizer = spy(new Derandomizer());
    private ConnectionRPG connectionRPG = mock(ConnectionRPG.class);
    private Printer printer = Printer.getMockInstance(spy(Printer.getInstance()));
    private Acter acter1 = new Hero("Hero 1", RoleClass.BARBARIAN, 20, 5, 3, 46);
    private Acter acter2 = new Hero("Hero 2", RoleClass.BARBARIAN, 20, 5, 3, 26);
    private Acter acter3 = new Hero("Hero 3", RoleClass.BARBARIAN, 20, 5, 3, 25);
    private Acter acter4 = new Troll("Troll 1", 20, 5, 3, 3);
    private ActersRepository actersRepository = new ActersRepository(0,0, connectionRPG);
    private SortedActersList sortedActersList = actersRepository.getSortedActers();
    private IRandom random = new Derandomizer();

    @BeforeClass
    public static void setUpOutputStream() {
        System.setOut(new PrintStream(outContent));
    }

    @Before
    public void setUp(){
        sortedActersList.addActer(new ActerWithInitiative(acter1,random));
        sortedActersList.addActer(new ActerWithInitiative(acter2,random));
        sortedActersList.addActer(new ActerWithInitiative(acter3,random));
        sortedActersList.addActer(new ActerWithInitiative(acter4,random));
    }

    @Test
    public void constructor_shouldSetActersRemovedActersAndDispatcher(){
        SortedActersList acters = new SortedActersList();
        ArrayList<Acter> removedActers = new ArrayList<>();
        CommandDispatcher dispatcher = new CommandDispatcher();
        Round round = new Round(acters, removedActers, dispatcher, derandomizer);

        Assert.assertEquals(acters, round.getActers());
        Assert.assertEquals(removedActers, round.getRemovedActers());
        Assert.assertEquals(dispatcher, round.getDispatcher());
    }

    @Test
    public void runRound_shouldPrint4Attacks_givenThereAreOpponents(){

        doReturn(1).when(derandomizer).nextInt(0,4);
        round = new Round(sortedActersList, new ArrayList<>(), new CommandDispatcher(), derandomizer);
        round.runRound();


        verify(derandomizer, times(4)).nextInt(0,4);
        verify(printer, times(4)).println(matches(".+ attacked .*"));
    }

    @Test
    public void runRound_shouldPrint3HeroHadNoOneToAttack_givenThereAreNoOpponents(){
        doReturn(1).when(derandomizer).nextInt(0,4);

        sortedActersList = new SortedActersList();
        sortedActersList.addActer(new ActerWithInitiative(acter1, random));
        sortedActersList.addActer(new ActerWithInitiative(acter2, random));
        sortedActersList.addActer(new ActerWithInitiative(acter3, random));

        round = new Round(sortedActersList, new ArrayList<>(), new CommandDispatcher(), derandomizer);
        round.runRound();
        verify(printer, times(3)).println(matches(".* had no one to attack."));
    }

    @Test
    public void battle_shouldPrint4ActersDecidedToSkipRound_givenRandomIs4(){

        doReturn(4).when(derandomizer).nextInt(0,4);
        round = new Round(sortedActersList, new ArrayList<>(), new CommandDispatcher(), derandomizer);
        round.runRound();
        verify(printer, times(4)).println(matches(".* decided to skip round."));
    }

    @Test
    public void battle_shouldPrintTrollDied_givenHealthIsLessThan1(){
        doReturn(0).when(derandomizer).nextInt(0,4);
        sortedActersList.addActer(new ActerWithInitiative(new Hero("Hero 4", RoleClass.BARBARIAN, 20, 18, 1, 24),random));
        round = new Round(sortedActersList, new ArrayList<>(), new CommandDispatcher(), derandomizer);
        round.runRound();
        verify(printer, times(4)).println(matches(".* attacked Troll 1."));
        verify(printer, times(1)).println("Troll 1 has died.");
        verify(printer, never()).println("Troll 1 attacked Hero 1.");
    }

    @Test
    public void battle_shouldPrintTrollLeft_givenHealthIsLessThan2AndMoreThan0(){
        doReturn(0).when(derandomizer).nextInt(0,4);
        sortedActersList.addActer(new ActerWithInitiative(new Hero("Hero 4", RoleClass.BARBARIAN, 20, 16, 1, 24), random));
        round = new Round(sortedActersList, new ArrayList<>(), new CommandDispatcher(), derandomizer);
        round.runRound();
        verify(printer, times(5)).println(matches(".* attacked .*"));
        verify(printer, times(1)).println("Troll 1 has left the battle. Such a coward.");
    }


    @AfterClass
    public static void tearDown(){
        System.setOut(System.out);
    }


}
package commands.concrete_commands;

import acters.Acter;
import acters.enemy.Troll;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RunAwayTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    Acter acter = new Troll("Troll 1", 20, 10, 5, 2);
    RunAway runAway = new RunAway(acter);

    @Before
    public void setUpStream(){
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStream(){
        System.setOut(System.out);
    }

    @Test
    public void execute_shouldPrintMessage(){
        runAway.execute();
        Assert.assertEquals("Troll 1 has left the battle. Such a coward.\r\n", outContent.toString());

    }

}

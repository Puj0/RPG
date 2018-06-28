package commands.concrete_commands;

import acters.Acter;
import acters.enemy.Troll;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SkipRoundTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private Acter acter = new Troll("Troll 1", 20, 10, 5, 2);
    private SkipRound skipRound = new SkipRound(acter);

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
        skipRound.execute();
        Assert.assertEquals("Troll 1 decided to skip round.\n", outContent.toString());
    }
}

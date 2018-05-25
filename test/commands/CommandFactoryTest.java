package commands;

import acters.Acter;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CommandFactoryTest {

    private CommandFactory commandFactory = new CommandFactory();
    private Acter attacker = mock(Acter.class);
    private Acter defender = mock(Acter.class);

    @Test
    public void createAttack_shouldCreateAttack(){
        assertNotNull(commandFactory.createAttack(attacker, defender));
    }

    @Test
    public void createRunAway_shouldCreateRunAway(){
        assertNotNull(commandFactory.createRunAway(attacker));
    }

    @Test
    public void createAttack_shouldCreateSkipRound(){
        assertNotNull(commandFactory.createSkipRound(attacker));
    }

}
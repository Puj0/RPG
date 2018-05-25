package commands;

import commands.concrete_commands.RunAway;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandDispatcherTest {

    private RunAway runAway = mock(RunAway.class);
    private CommandDispatcher commandDispatcher = new CommandDispatcher();

    @Test
    public void setCommand_shouldExecuteCommand(){
        commandDispatcher.setCommand(runAway);
        verify(runAway).execute();
    }

}
package commands;

import commands.concrete_commands.AttackTests;
import commands.concrete_commands.RunAwayTests;
import commands.concrete_commands.SkipRoundTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AttackTests.class,
        RunAwayTests.class,
        SkipRoundTests.class,
        CommandDispatcherTest.class,
        CommandFactoryTest.class
})

public class CommandsTestSuite {
}

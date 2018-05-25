package acters;

import acters.acters_repository_test.ActerWithInitiativeTests;
import acters.acters_repository_test.ActersRepositoryTests;
import acters.acters_repository_test.SortedActersListTests;
import acters.enemy_test.AnimalTests;
import acters.enemy_test.TrollTest;
import acters.heroes_test.HeroTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AnimalTests.class,
        TrollTest.class,
        HeroTests.class,
        ActersRepositoryTests.class,
        ActerWithInitiativeTests.class,
        SortedActersListTests.class
})

public class ActersTestSuite {
}

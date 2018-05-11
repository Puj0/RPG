import acters_test.enemy_test.AnimalTests;
import acters_test.enemy_test.TrollTest;
import acters_test.heroes_test.HeroTests;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AnimalTests.class, TrollTest.class, HeroTests.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}

package game;

import acters.acters_repository.ActersRepository;
import acters.acters_repository.IActersRepository;
import database.ConnectionRPG;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class GameModuleTests {

    private ConnectionRPG connectionRPG = mock(ConnectionRPG.class);
    private final int ONE_ROUND = 1;

    private IActersRepository actersRepository = new ActersRepository(1, 0, connectionRPG);
    private Printer printer = new Printer();
    private GameModule gameModule = new GameModule();

    @Test
    public void provideGame_shouldProvideGame(){
        Assert.assertEquals(Game.class, gameModule.provideGame(actersRepository, printer, ONE_ROUND).getClass());
    }
}
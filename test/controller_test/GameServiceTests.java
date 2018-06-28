package controller_test;

import acters.Acter;
import acters.acters_repository.ActersRepository;
import controller.GameService;
import database.ConnectionRPG;
import game.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class GameServiceTests {

    private List<Acter> acters;
    ActersRepository actersRepository;

//    private GameService gameService = spy(new GameService(actersRepository, 1));


    @Before
    public void setUp() {
    }

    @Ignore
    @Test
    public void constructor_shouldSetConnectionAndRounds(){
        try {
            ConnectionRPG connectionRPG = ConnectionRPG.getInstance();
            int rounds = 1;
//            GameService gameService = new GameService(actersRepository, rounds);
//            Assert.assertEquals(connectionRPG, gameService.getConnection());
//            Assert.assertEquals(rounds, gameService.getRounds());
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
//
//    @Test
//    public void createGame_shouldCreateGame_givenGetAllWasCalled() {
//        gameService.getAll();
//        gameService.createGame();
//        assertNotNull(gameService.getGame());
//    }

}
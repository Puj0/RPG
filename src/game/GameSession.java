package game;

import javax.inject.Inject;

public class GameSession {

    @Inject
    public Game game;

    @Inject
    public GameSession() {
    }

   public void start(){
        game.runGame();
    }

}

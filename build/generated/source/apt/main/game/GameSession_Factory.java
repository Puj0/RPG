package game;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GameSession_Factory implements Factory<GameSession> {
  private final Provider<Game> gameProvider;

  public GameSession_Factory(Provider<Game> gameProvider) {
    this.gameProvider = gameProvider;
  }

  @Override
  public GameSession get() {
    GameSession instance = new GameSession();
    GameSession_MembersInjector.injectGame(instance, gameProvider.get());
    return instance;
  }

  public static GameSession_Factory create(Provider<Game> gameProvider) {
    return new GameSession_Factory(gameProvider);
  }

  public static GameSession newGameSession() {
    return new GameSession();
  }
}

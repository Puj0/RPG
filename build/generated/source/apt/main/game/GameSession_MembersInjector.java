package game;

import dagger.MembersInjector;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GameSession_MembersInjector implements MembersInjector<GameSession> {
  private final Provider<Game> gameProvider;

  public GameSession_MembersInjector(Provider<Game> gameProvider) {
    this.gameProvider = gameProvider;
  }

  public static MembersInjector<GameSession> create(Provider<Game> gameProvider) {
    return new GameSession_MembersInjector(gameProvider);
  }

  @Override
  public void injectMembers(GameSession instance) {
    injectGame(instance, gameProvider.get());
  }

  public static void injectGame(GameSession instance, Game game) {
    instance.game = game;
  }
}

package DI;

import acters.ActersRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import game.Game;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GameModule_ProvideGameFactory implements Factory<Game> {
  private final GameModule module;

  private final Provider<ActersRepository> actersRepositoryProvider;

  public GameModule_ProvideGameFactory(
      GameModule module, Provider<ActersRepository> actersRepositoryProvider) {
    this.module = module;
    this.actersRepositoryProvider = actersRepositoryProvider;
  }

  @Override
  public Game get() {
    return Preconditions.checkNotNull(
        module.provideGame(actersRepositoryProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static GameModule_ProvideGameFactory create(
      GameModule module, Provider<ActersRepository> actersRepositoryProvider) {
    return new GameModule_ProvideGameFactory(module, actersRepositoryProvider);
  }

  public static Game proxyProvideGame(GameModule instance, ActersRepository actersRepository) {
    return Preconditions.checkNotNull(
        instance.provideGame(actersRepository),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

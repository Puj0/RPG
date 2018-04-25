package DI;

import acters.ActersRepository;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import game.Game;
import game.GameSession;
import game.GameSession_MembersInjector;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerGameComponent implements GameComponent {
  private Provider<ActersRepository> provideActersRepositoryProvider;

  private Provider<Game> provideGameProvider;

  private DaggerGameComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideActersRepositoryProvider =
        DoubleCheck.provider(
            RepositoryModule_ProvideActersRepositoryFactory.create(builder.repositoryModule));
    this.provideGameProvider =
        DoubleCheck.provider(
            GameModule_ProvideGameFactory.create(
                builder.gameModule, provideActersRepositoryProvider));
  }

  @Override
  public void inject(GameSession gameSession) {
    injectGameSession(gameSession);
  }

  private GameSession injectGameSession(GameSession instance) {
    GameSession_MembersInjector.injectGame(instance, provideGameProvider.get());
    return instance;
  }

  public static final class Builder {
    private RepositoryModule repositoryModule;

    private GameModule gameModule;

    private Builder() {}

    public GameComponent build() {
      if (repositoryModule == null) {
        this.repositoryModule = new RepositoryModule();
      }
      if (gameModule == null) {
        throw new IllegalStateException(GameModule.class.getCanonicalName() + " must be set");
      }
      return new DaggerGameComponent(this);
    }

    public Builder gameModule(GameModule gameModule) {
      this.gameModule = Preconditions.checkNotNull(gameModule);
      return this;
    }

    public Builder repositoryModule(RepositoryModule repositoryModule) {
      this.repositoryModule = Preconditions.checkNotNull(repositoryModule);
      return this;
    }
  }
}

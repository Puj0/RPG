package DI;

import acters.ActersRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class RepositoryModule_ProvideActersRepositoryFactory
    implements Factory<ActersRepository> {
  private final RepositoryModule module;

  public RepositoryModule_ProvideActersRepositoryFactory(RepositoryModule module) {
    this.module = module;
  }

  @Override
  public ActersRepository get() {
    return Preconditions.checkNotNull(
        module.provideActersRepository(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static RepositoryModule_ProvideActersRepositoryFactory create(RepositoryModule module) {
    return new RepositoryModule_ProvideActersRepositoryFactory(module);
  }

  public static ActersRepository proxyProvideActersRepository(RepositoryModule instance) {
    return Preconditions.checkNotNull(
        instance.provideActersRepository(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

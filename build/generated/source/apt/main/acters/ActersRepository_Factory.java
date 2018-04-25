package acters;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ActersRepository_Factory implements Factory<ActersRepository> {
  private static final ActersRepository_Factory INSTANCE = new ActersRepository_Factory();

  @Override
  public ActersRepository get() {
    return new ActersRepository();
  }

  public static ActersRepository_Factory create() {
    return INSTANCE;
  }
}

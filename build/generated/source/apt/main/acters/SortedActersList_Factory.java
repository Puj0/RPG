package acters;

import acters.acters_repository.SortedActersList;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class SortedActersList_Factory implements Factory<SortedActersList> {
  private static final SortedActersList_Factory INSTANCE = new SortedActersList_Factory();

  @Override
  public SortedActersList get() {
    return new SortedActersList();
  }

  public static SortedActersList_Factory create() {
    return INSTANCE;
  }
}

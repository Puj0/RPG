package game;

import acters.acters_repository.RepositoryModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {GameModule.class, RepositoryModule.class})
public interface GameComponent {
    Game inject();
}


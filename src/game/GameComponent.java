package game;

import acters.RepositoryModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {GameModule.class, RepositoryModule.class})
public interface GameComponent {
    Game inject();
}


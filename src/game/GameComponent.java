package game;

import acters.acters_repository.RepositoryModule;
import controller.GameService;
import controller.RpgModule;
import database.ConnectionModule;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {GameModule.class, RepositoryModule.class, ConnectionModule.class, RpgModule.class})
public interface GameComponent {
    GameService inject();
}


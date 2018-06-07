package game;

import acters.acters_repository.RepositoryModule;
import dagger.Component;
import database.DbModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {GameModule.class, RepositoryModule.class, DbModule.class})
public interface GameComponent {
    Game inject();
}


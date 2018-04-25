package DI;

import dagger.Component;
import game.GameSession;
import javax.inject.Singleton;

@Singleton
@Component(modules = {GameModule.class,RepositoryModule.class})
public interface GameComponent {
    void inject(GameSession gameSession);
}


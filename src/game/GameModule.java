package game;

import acters.acters_repository.IActersRepository;
import commands.CommandDispatcher;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class GameModule {
    private int rounds;

    public GameModule(int rounds) {
        this.rounds = rounds;
    }
    @Singleton
    @Provides
    Game provideGame(IActersRepository actersRepository){
     return new Game.GameBuilder(rounds)
             .addActers(actersRepository)
             .addCommandDispatcher(new CommandDispatcher())
             .build();
    }

}

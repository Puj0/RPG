package game;

import acters.acters_repository.IActersRepository;
import commands.CommandDispatcher;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class GameModule {

    @Singleton
    @Provides
    Game provideGame(IActersRepository actersRepository, Printer printer, int rounds) {
        return new Game.GameBuilder(rounds)
                .addActers(actersRepository)
                .addCommandDispatcher(new CommandDispatcher())
                .addPrinter(printer)
                .build();
    }

    @Singleton
    @Provides
    Printer providePrinter() {
        return Printer.getInstance();
    }

}

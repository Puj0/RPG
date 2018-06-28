package controller;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;


@Module
public class RpgModule {
    private int rounds;

    public RpgModule(int rounds) {
        this.rounds = rounds;
    }

    @Singleton
    @Provides
    public int provideRounds() {
        return rounds;
    }

}

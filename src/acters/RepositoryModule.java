package acters;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RepositoryModule {
    @Singleton
    @Provides
    IActersRepository provideActersRepository(){
        return new ActersRepository();
    }
}

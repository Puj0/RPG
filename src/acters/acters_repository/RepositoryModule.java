package acters.acters_repository;

import dagger.Module;
import dagger.Provides;
import database.ConnectionRPG;

import javax.inject.Singleton;

@Module
public class RepositoryModule {
    @Singleton
    @Provides
    IActersRepository provideActersRepository(ConnectionRPG connectionRPG){
        return new ActersRepository(120,5, connectionRPG);
    }
}

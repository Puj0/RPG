package DI;

import acters.ActersRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RepositoryModule {
    @Singleton
    @Provides
    ActersRepository provideActersRepository(){
        return new ActersRepository();
    }
}

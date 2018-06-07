package database;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DbModule {
    @Singleton
    @Provides
    ConnectionRPG provideConnectionRPG(){
        try {
            return ConnectionRPG.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

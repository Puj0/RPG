package database;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ConnectionModule {
    @Singleton
    @Provides
    public ConnectionRPG provideConnectionRPG(){
        try {
            return ConnectionRPG.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package database_test;

import database.ConnectionModule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionModuleTests {

    ConnectionModule connectionModule = new ConnectionModule();

    @Test
    public void provideConnectionRPG_shouldProvideConnectionRPG(){
        assertNotNull(connectionModule.provideConnectionRPG());
    }

}
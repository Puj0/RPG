package database_test;

import database.ConnectionRPG;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionRPGTests {

    private static ConnectionRPG instance;

    @Before
    public void setUp(){
        try {
            instance = ConnectionRPG.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getInstance_shouldReturnInstance() {
        Assert.assertNotNull(instance);
    }

    @Test
    public void connectTo() {

    }

    @Test
    public void createRPGTable() {
    }

    @Test
    public void createActerTable() {
    }

    @Test
    public void insertValueToRPGTable() {
    }

    @Test
    public void insertValueToActerTable() {
    }

    @Test
    public void getActersFromActerTable() {
    }
}
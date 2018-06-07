package acters.acters_repository_test;

import acters.acters_repository.ActersRepository;
import database.ConnectionRPG;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import static org.mockito.Mockito.mock;

public class ActersRepositoryTests {

    @Spy
    private ActersRepository actersRepository;
    private ConnectionRPG connectionRPG = mock(ConnectionRPG.class);

    @Before
    public void setUp(){
        actersRepository = new ActersRepository(10,0, connectionRPG);
    }

    @Test
    public void getSortedActers_shouldNotBeNull(){
        Assert.assertNotNull(actersRepository.getSortedActers());
    }

}

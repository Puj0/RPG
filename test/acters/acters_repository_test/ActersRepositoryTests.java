package acters.acters_repository_test;

import acters.acters_repository.ActersRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

public class ActersRepositoryTests {

    @Spy
    private ActersRepository actersRepository;

    @Before
    public void setUp(){
        actersRepository = new ActersRepository(10,0);
    }

    @Test
    public void getSortedActers_shouldNotBeNull(){
        Assert.assertNotNull(actersRepository.getSortedActers());
    }

}

package acters.acters_repository;

import database.ConnectionRPG;
import database.DbModule;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.spy;

public class RepositoryModuleTest {

    private RepositoryModule repositoryModule = spy(new RepositoryModule());

    @Test
    public void provideActersRepository_shouldProvideActersRepository(ConnectionRPG connectionRPG) {
        Assert.assertEquals(ActersRepository.class, repositoryModule.provideActersRepository(connectionRPG).getClass());
    }
}
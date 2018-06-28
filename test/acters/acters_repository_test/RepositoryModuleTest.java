package acters.acters_repository_test;

import acters.acters_repository.ActersRepository;
import acters.acters_repository.RepositoryModule;
import database.ConnectionRPG;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class RepositoryModuleTest {

    private RepositoryModule repositoryModule = spy(new RepositoryModule());
    ConnectionRPG connectionRPG = mock(ConnectionRPG.class);

    @Test
    public void provideActersRepository_shouldProvideActersRepository() {
        Assert.assertEquals(ActersRepository.class, repositoryModule.provideActersRepository(connectionRPG).getClass());
    }
}
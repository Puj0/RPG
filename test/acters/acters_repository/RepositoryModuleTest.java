package acters.acters_repository;

import com.sun.source.tree.AssertTree;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class RepositoryModuleTest {

    RepositoryModule repositoryModule = spy(new RepositoryModule());

    @Test
    public void provideActersRepository_shouldProvideActersRepository() {
        Assert.assertEquals(ActersRepository.class, repositoryModule.provideActersRepository().getClass());
    }
}
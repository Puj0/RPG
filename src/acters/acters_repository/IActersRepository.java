package acters.acters_repository;

public interface IActersRepository {
    void createCharacters(int numberOfHeroes, int range);
    SortedActersList getSortedActers();
}

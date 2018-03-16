package acters;

import acters.enemy.Animal;
import acters.enemy.Troll;
import acters.hero.Hero;
import acters.hero.RoleClass;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.google.common.collect.*;

//Klasa koja će napraviti sve aktere... Postavlja im inicijative,
//sortira ih po njima, reguliše da li su živi ili ne. Možda još nešto bude dodato.
//Mare će me koriti jer ovo nije po SOLID-u.

public class Acters {

    public SortedSetMultimap<Integer,Acter> sortedActers;
    private ThreadLocalRandom random = ThreadLocalRandom.current();

    public Acters(){
        sortedActers = TreeMultimap.create(Comparator.<Integer>naturalOrder().reversed(), Ordering.arbitrary());
    }

    public void createCharacters() {

        int numOfHeroes = random.nextInt(4, 17);
        int numOfEnemies = random.nextInt(numOfHeroes, numOfHeroes*2);

        createHeroes(numOfHeroes);
        createEnemies(numOfEnemies);
    }

    private void createEnemies(int numOfEnemies) {
        for(int i = 0; i < numOfEnemies; i++) {
            boolean isTroll = (random.nextInt(1, 11) % 2) == 0;
            if (isTroll) {
                Troll newTroll = new Troll("Troll " + (i+1), random.nextInt(10, 26),
                        random.nextInt(3, 11), random.nextInt(2, 7),
                        random.nextInt(1, 10));
                int newInitiative = calculateInitiative(newTroll.getInitiative());
                System.out.println(newTroll.getName() + " has initiative: " + newInitiative);
                this.sortedActers.put(newInitiative, newTroll);
            } else {
                Animal newAnimal = new Animal("Animal " + (i+1), random.nextInt(5, 16),
                        random.nextInt(0, 6), random.nextInt(2, 5),
                        random.nextInt(1, 5));
                int newInitiative = calculateInitiative(newAnimal.getInitiative());
                this.sortedActers.put(newInitiative, newAnimal);
            }
        }
    }

    private void createHeroes(int numOfHeroes){
        for(int i = 0; i < numOfHeroes; i++) {
            Hero newHero = new Hero("Hero " + (i+1), RoleClass.values()[i%RoleClass.values().length],
                    random.nextInt(10, 26), random.nextInt(3, 11),
                    random.nextInt(2, 7), random.nextInt(1, 10));
            int newInitiative = calculateInitiative(newHero.getInitiative());
            System.out.println(newHero.getName() + " has initiative: " + newInitiative);
            this.sortedActers.put(newInitiative, newHero);
        }
    }

    private int calculateInitiative(int initiative) {
        return initiative + random.nextInt(1, 20);
    }

    public List<? extends Acter> getHeroes(){

        ArrayList<? extends Acter> acters = new ArrayList<>(sortedActers.values());

        return acters.stream()
                .filter(s -> s.getClass()==Hero.class)
                .collect(Collectors.toList());
    }

    public List<? extends Acter> getTrolls(){

        ArrayList<? extends Acter> acters = new ArrayList<>(sortedActers.values());

        return acters.stream()
                .filter(s -> s.getClass()==Troll.class)
                .collect(Collectors.toList());
    }

    public List<? extends Acter> getAnimals(){

        ArrayList<? extends Acter> acters = new ArrayList<>(sortedActers.values());

        return acters.stream()
                .filter(s -> s.getClass()==Animal.class)
                .collect(Collectors.toList());
    }

    public boolean gameDone() {
        return (getHeroes().isEmpty() || getTrolls().isEmpty());
    }

    public void remove(Acter acter) {
        sortedActers.values().remove(acter);
    }
}

import acters.Acter;
import acters.Acters;
import acters.enemy.Animal;
import acters.enemy.Troll;
import acters.hero.Hero;
import actions.Attack;
import actions.SkipRound;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Serializable{

    private static Acters acters = new Acters();
    private static ArrayList<Acter> removedActers = new ArrayList<>();
    private static ArrayList<Troll> trolls;
    private static ArrayList<Animal> animals;
    private static ArrayList<Hero> heroes;

    Game(){
        acters.createCharacters();
        trolls = (ArrayList<Troll>) acters.getTrolls();
        animals = (ArrayList<Animal>) acters.getAnimals();
        heroes = (ArrayList<Hero>) acters.getHeroes();
    }

    public boolean gameDone() {
        return acters.gameDone();
    }

    public void runRound(){
        battle();
    }

    private static void battle() {

        Collection<Acter> list = acters.sortedActers.values();
        for (Acter acter : list) {
            if (!removedActers.contains(acter)) {
                if (acterIsAttacking()) {
                    if (acter.getClass().equals(Hero.class)) {
                        fight(acter, trolls);
                    }
                    if (acter.getClass().equals(Troll.class)) {
                        fight(acter, heroes);
                    }
                } else {
                    new SkipRound(acter);
                }
            }
        }
        for (Acter acter: removedActers) {
            acters.remove(acter);
        }
    }

    private static boolean acterIsAttacking(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return (random.nextInt(0,4) > 1);
    }

    private static void fight(Acter attacker, ArrayList<? extends Acter> defenders){
        ThreadLocalRandom random = ThreadLocalRandom.current();

        if (!animals.isEmpty() && attacker.getHealthPoints() < 5){
            Animal animal = animals.get(random.nextInt(0, animals.size()));
            new Attack(attacker, animal);
            if (animal.getHealthPoints()<=0) {
                System.out.println(attacker.getName() + " replenished some health");
                removedActers.add(animal);
                attacker.replenishHealth();
            }
        } else {
            if (!defenders.isEmpty()) {
                Acter defender = defenders.get(random.nextInt(0, defenders.size()));
                new Attack(attacker, defender);
                if (defender.getHealthPoints()<=0) {
                    System.out.println(defender.getName() + " died.");
                    removedActers.add(defender);
                    defenders.remove(defender);
                }
            }
        }
    }

    public static void outcome() {
        if (heroes.isEmpty()) {
            System.out.println("Heroes lost!");
        } else {
            System.out.println("Heroes were victorious");
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

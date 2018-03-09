import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import acters.enemy.Animal;
import acters.enemy.Troll;
import acters.hero.Hero;
import acters.hero.Role;
import actions.Action;
import actions.Attack;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Hero> heroes = new ArrayList<Hero>();
		ArrayList<Troll> trolls = new ArrayList<Troll>();
		ArrayList<Animal> animals = new ArrayList<Animal>();
		
		createCharacters(heroes, trolls, animals);
		
		battle(heroes, trolls, animals);
		
		outcome(heroes);
		
	}

	private static void createCharacters(ArrayList<Hero> heroes, ArrayList<Troll> trolls, ArrayList<Animal> animals) {
		
		ThreadLocalRandom random = ThreadLocalRandom.current();
		
		int numOfHeroes = random.nextInt(4, 7);
		int numOfEnemies = random.nextInt(numOfHeroes, numOfHeroes*2);
		
		for(int i = 0; i < numOfHeroes; i++) {
			heroes.add(new Hero("Hero " + (i+1), Role.values()[i], random.nextInt(10, 26), random.nextInt(3, 11), random.nextInt(2, 7)));
		}
		
		for(int i = 0; i < numOfEnemies; i++) {
			boolean isTroll = (random.nextInt(1, 11) % 3) == 0;
			if (isTroll) {
				trolls.add(new Troll("Troll " + (i+1), random.nextInt(10, 26), random.nextInt(3, 11), random.nextInt(2, 7)));
			} else {
				animals.add(new Animal("Animal " + (i+1), random.nextInt(5, 16), random.nextInt(0, 6), random.nextInt(2, 5)));
			}
		}
	}
	
	private static void battle(ArrayList<Hero> heroes, ArrayList<Troll> trolls, ArrayList<Animal> animals) {
		
		ThreadLocalRandom random = ThreadLocalRandom.current();
		
		while (!heroes.isEmpty() && !trolls.isEmpty()) {
			for (Hero h : heroes) {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!animals.isEmpty() && h.getHealthPoints() < 5) {
					Animal animal = animals.get(random.nextInt(animals.size()));
					h.doAction(new Attack(h, animal));
//					h.attack(animal);
					if (animal.getHealthPoints()<=0) {
						System.out.println(h.getName() + " replenished some health");
						animals.remove(animal);
						h.setHealthPoints(h.getHealthPoints() + 3);
					}
				} else {
					if (!trolls.isEmpty()) {
						Troll troll = trolls.get(random.nextInt(trolls.size()));
						Action.doAction(new Attack(h, troll));
//						h.attack(troll);
						if (troll.getHealthPoints()<=0) {
							System.out.println(troll.getName() + " died.");
							trolls.remove(troll);
						}
					}
				}
			}
			for (Troll t : trolls) {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!animals.isEmpty() && t.getHealthPoints() < 5) {
					Animal animal = animals.get(random.nextInt(0, animals.size()));
//					t.attack(animal);
					Action.doAction(new Attack(t, animal));
					if (animal.getHealthPoints()<=0) {
						System.out.println(t.getName() + " replenished some health");
						animals.remove(animal);
						t.setHealthPoints(t.getHealthPoints() + 2);
					}
				} else {
					if (!trolls.isEmpty()) {
						Hero hero = heroes.get(random.nextInt(0, heroes.size()));
//						t.attack(hero);
						Action.doAction(new Attack(t, hero));
						if (hero.getHealthPoints()<=0) {
							System.out.println(hero.getName() + " died.");
							heroes.remove(hero);
						}
					}
				}
			}
		}
		
	}
	
	private static void outcome(ArrayList<Hero> heroes) {

		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (heroes.isEmpty()) {
			System.out.println("Heroes lost!");
		} else {
			System.out.println("Heroes were victorious");
		}	
	}
}



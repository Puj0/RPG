package acters.acters_repository;

import acters.Acter;
import database.ConnectionRPG;
import game.IRandom;
import acters.enemy.Animal;
import acters.enemy.Troll;
import acters.hero.Hero;
import acters.hero.RoleClass;
import game.ThreadRandom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;


public class ActersRepository implements IActersRepository {

    private SortedActersList sortedActers;
    private IRandom random = new ThreadRandom();
    private ConnectionRPG connectionRPG;

    public ActersRepository(int numberOfHeroes, int range, ConnectionRPG connectionRPG) {
        this.connectionRPG = connectionRPG;
        sortedActers = new SortedActersList();
        createCharacters(numberOfHeroes, range);
        createRPGTable();
    }

    @Override
    public void createCharacters(int numberOfHeroes, int range) {

        int numOfHeroes = random.nextInt(numberOfHeroes, numberOfHeroes + range + 1);
        int numOfEnemies = random.nextInt(numOfHeroes, numOfHeroes * 2 + 1);

        createActerTable();
        createHeroes(numOfHeroes);
        createEnemies(numOfEnemies);
    }

    private void createHeroes(int numOfHeroes) {
        for (int i = 0; i < numOfHeroes; i++) {
            Hero newHero = new Hero("Hero " + (i + 1), RoleClass.values()[i % RoleClass.values().length],
                    random.nextInt(10, 26), random.nextInt(3, 11),
                    random.nextInt(2, 7), random.nextInt(1, 10));
            addActerToSortedActers(newHero);
        }
    }

    private void createEnemies(int numOfEnemies) {
        boolean trollDoesNotExist = true;
        for (int i = 0; i < numOfEnemies; i++) {
            boolean isTroll = (trollDoesNotExist) || (random.nextInt(1, 11) % 2) == 1;
            if (isTroll) {
                trollDoesNotExist = false;
                Troll newTroll = new Troll("Troll " + (i + 1), random.nextInt(10, 26),
                        random.nextInt(3, 11), random.nextInt(2, 7),
                        random.nextInt(1, 10));
                addActerToSortedActers(newTroll);
            } else {
                Animal newAnimal = new Animal("Animal " + (i + 1), random.nextInt(5, 16),
                        random.nextInt(0, 6), random.nextInt(2, 5),
                        random.nextInt(1, 5));
                addActerToSortedActers(newAnimal);
            }
        }
    }

    private void createRPGTable() {
        String createString = "CREATE TABLE IF NOT EXISTS RPG "
                + "(Game INTEGER auto_increment, "
                + " rounds INTEGER, "
                + " result VARCHAR(255),"
                + " PRIMARY KEY ( Game ))";
        connectionRPG.openConnection();
        applyCommand(createString);
    }

    private void createActerTable() {
        connectionRPG.openConnection();
        String createString = "CREATE TABLE IF NOT EXISTS ACTER "
                + "(acter INTEGER auto_increment, "
                + " name VARCHAR(255),"
                + " role VARCHAR(255),"
                + " health INTEGER, "
                + " attack INTEGER, "
                + " defence INTEGER, "
                + " initiative INTEGER,"
                + " PRIMARY KEY ( acter ))";
        applyCommand(createString);
        try {
            connectionRPG.closeConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public ActersRepository getAll() {
        try {
            connectionRPG.openConnection();
            ResultSet resultSet = connectionRPG.select("SELECT * FROM ACTER");
            try {
                Acter newActer = null;
                while (resultSet.next()) {
                    String[] token = resultSet.getString(2).split(" ");
                    switch (token[0]) {
                        case "Hero":
                            RoleClass roleClass = RoleClass.valueOf(resultSet.getString(3));
                            newActer = new Hero(resultSet.getString(2), roleClass, resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
                            break;
                        case "Troll":
                            newActer = new Troll(resultSet.getString(2), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
                            break;
                        case "Animal":
                            newActer = new Animal(resultSet.getString(2), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
                            break;
                    }
                    if (newActer != null) {
                        this.getSortedActers().addActer(new ActerWithInitiative(newActer, random));
                    }
                }
                resultSet.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } finally {
            try {
                connectionRPG.closeConnection();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return this;
    }

    private void applyCommand(String createString) {
        try {
            connectionRPG.applyCommand(createString);
            if (connectionRPG != null) {
                connectionRPG.closeConnection();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void addActersToDatabase(ActerWithInitiative[] acters) {
        connectionRPG.openConnection();
        try {
            Arrays.stream(acters)
                .map(ActerWithInitiative::getActer)
                .forEach(acter -> {
                    RoleClass roleClass = null;
                    if (acter instanceof Hero) {
                        roleClass = ((Hero) acter).getRoleClass();
                    }
                    try {
                        String columns = "name, role, health, attack, defence, initiative";
                        String insertQuery = "\'" + acter.getName() + "\', \'" + roleClass + "\', \'" + acter.getHealthPoints()
                                + "\', \'" + acter.getAttack() + "\', \'" + acter.getDefence() + "\', \'" + acter.getInitiative();
                        String command = "INSERT INTO ACTER (" + columns + ") values(" + insertQuery + ")";
                        connectionRPG.applyCommand(command);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        } finally {
            try {
                connectionRPG.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addActerToSortedActers(Acter acter) {
        sortedActers.addActer(new ActerWithInitiative(acter, random));
    }

    @Override
    public SortedActersList getSortedActers() {
        return sortedActers;
    }

}

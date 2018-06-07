package database;

import acters.Acter;
import acters.acters_repository.ActerWithInitiative;
import acters.acters_repository.SortedActersList;
import acters.enemy.Troll;
import acters.hero.Hero;
import acters.hero.RoleClass;
import dagger.Provides;
import game.IRandom;
import game.ThreadRandom;

import javax.inject.Inject;
import java.sql.*;

public class ConnectionRPG {

    Connection connection = null;
    Statement statement;
    private static ConnectionRPG instance;
    IRandom random = new ThreadRandom();


    private ConnectionRPG() throws Exception {
        connectTo("jdbc:h2:tcp://localhost/~/test2", "sa", "sa");
        Class.forName("org.h2.Driver");
        createRPGTable();
    }

    public static ConnectionRPG getInstance() throws Exception {
        if (instance == null){
            return instance = new ConnectionRPG();
        }
        return instance;
    }

    public void connectTo(String url, String user, String password) throws Exception{
        connection = DriverManager.getConnection(url, user, password);
    }

    public void createRPGTable() throws SQLException {
        String gamesTableSQL = "CREATE TABLE IF NOT EXISTS RPG "
                + "(Game INTEGER auto_increment, "
                + " rounds INTEGER, "
                + " result VARCHAR(255),"
                + " PRIMARY KEY ( Game ))";
        statement = connection.createStatement();
        statement.executeUpdate(gamesTableSQL);
//        statement.close();
    }

    public void createActerTable() throws SQLException{
        String acterTableSQL = "CREATE TABLE IF NOT EXISTS ACTER "
                + "(acter INTEGER auto_increment, "
                + " name VARCHAR(255),"
                + " role VARCHAR(255),"
                + " health INTEGER, "
                + " attack INTEGER, "
                + " defence INTEGER, "
                + " initiative INTEGER,"
                + " PRIMARY KEY ( acter ))";
        statement = connection.createStatement();
        statement.executeUpdate(acterTableSQL);
//        statement.close();
    }

    public void insertValueToRPGTable(int i, String result) throws SQLException {
        String insertSQL = "INSERT INTO RPG (rounds, result) values(?,?)";
        PreparedStatement insertPreparedStatement = connection.prepareStatement(insertSQL);
        insertPreparedStatement.setInt(1, i);
        insertPreparedStatement.setString(2,result);
        insertPreparedStatement.executeUpdate();
        insertPreparedStatement.close();
    }

    public void insertValueToActerTable(String name, RoleClass role, int health, int attack, int defence, int initiative) throws SQLException {
        String insertSQL = "INSERT INTO ACTER(name, role, health,attack,defence,initiative) values(?,?,?,?,?,?)";
        PreparedStatement insertPreparedStatement = connection.prepareStatement(insertSQL);
        insertPreparedStatement.setString(1,name);
        String roleString = "";
        if (role != null){
            roleString = role.toString();
        }
        insertPreparedStatement.setString(2, roleString);
        insertPreparedStatement.setInt(3, health);
        insertPreparedStatement.setInt(4, attack);
        insertPreparedStatement.setInt(5, defence);
        insertPreparedStatement.setInt(6, initiative);
        insertPreparedStatement.executeUpdate();
        insertPreparedStatement.close();
    }

    public SortedActersList queryActersFromActerTable(){
        SortedActersList actersList = new SortedActersList();
        String querySQL = "SELECT * FROM ACTER";
        try {
            ResultSet resultSet = statement.executeQuery(querySQL);
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
                        newActer = new Troll(resultSet.getString(2), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
                        break;
                }
                actersList.addActer(new ActerWithInitiative(newActer, random));
            }
            resultSet.close();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return actersList;
    }

}

package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

public class Database {
    private Connection connection;
    private Statement statement;

    public void insertData(Player player){
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users (username ,password, level, coins) VALUE ('"+player.getName()+"','"+player.getPassword()+"',"+player.getLevel()+",'"+player.getCoins()+"')");
            System.out.println("inserted");
        } catch (SQLException throwables) {
            System.out.println("error not insert");
        }
    }

    public void deleteData(Player player){
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users WHERE username='"+player.getName()+"'");
            System.out.println("deleted");
        } catch (SQLException throwables) {
            System.out.println("error not delete");
        }
    }

    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1273859022");
            System.out.println("connected");
        } catch (SQLException throwables) {
            System.out.println("error not connect");
        }
    }
}

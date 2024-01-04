package ru.goose.art.dao;

import org.springframework.stereotype.Component;
import ru.goose.art.models.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class PersonDAO {
    public PersonDAO() {

    }

    private static Connection connection;

    // Связь с БД
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\123\\Desktop\\java\\GooseArt\\src\\main\\webapp\\GooseArt_db.s3db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean save(Person person){
        connect();
        try{
            Statement statement = connection.createStatement();
            System.out.println(person.getName() + " " + person.getNumber());
            String SQL = "INSERT INTO APPLICATIONS VALUES('" + person.getName() + "', '" + person.getNumber() + "')";
            statement.executeUpdate(SQL);
            connection.close();
            return true;
        }catch (SQLException throwables){
            throwables.printStackTrace();
            return false;
        }
    }
}

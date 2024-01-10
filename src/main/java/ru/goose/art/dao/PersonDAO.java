package ru.goose.art.dao;

import org.springframework.stereotype.Component;
import ru.goose.art.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    // Добавление заявки
    public boolean save(Person person) {
        connect();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO APPLICATIONS VALUES(?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getNumber());
            preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public List<Person> getAllData() {
        List<Person> applications = new ArrayList<Person>();
        connect();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM APPLICATIONS";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                applications.add(new Person(resultSet.getString("name"),
                        resultSet.getString("number"), "no"));
            }
            connection.close();
            return applications;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


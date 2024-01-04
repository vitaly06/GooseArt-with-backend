package ru.goose.art.dao;

import org.springframework.stereotype.Component;
import ru.goose.art.models.Admin;

import java.sql.*;
import java.util.Objects;

@Component
public class AdminDAO {
    public AdminDAO() {

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
    // Вход в панель админа
    public String adminLogin(Admin admin) {
        connect();
        try {
            Statement statement = connection.createStatement();
            System.out.println(admin.getLogin() + " " + admin.getPassword());
            String SQL = "SELECT NAME FROM ADMINS WHERE LOGIN = '" + admin.getLogin() + "' AND PASSWORD = '" + admin.getPassword() + "';";
            ResultSet resultSet = statement.executeQuery(SQL);
            String name = resultSet.getString("name");
            System.out.println(name);
            connection.close();
            return name;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "";
        }
    }
}

package ru.goose.art.dao;

import org.springframework.stereotype.Component;
import ru.goose.art.models.Admin;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
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
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT NAME FROM ADMINS WHERE LOGIN = ? AND PASSWORD = ?");
            preparedStatement.setString(1, admin.getLogin());
            preparedStatement.setString(2, admin.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            String name = resultSet.getString("name");
            connection.close();
            return name;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "";
        }
    }
}

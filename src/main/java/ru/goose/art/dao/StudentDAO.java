package ru.goose.art.dao;

import org.springframework.stereotype.Component;
import ru.goose.art.models.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class StudentDAO {
    public StudentDAO(){}

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

    public List<Student> addStudents(List<Student> students){
        connect();
        try{
            Statement statement = connection.createStatement();
            for(Student student : students){
                System.out.println("1: " + student.getName() + " " + student.getStatus());
                if (Objects.equals(student.getStatus(), "yes")) {
                    String SQL = "INSERT INTO STUDENTS VALUES('" + student.getName() + "', '" + student.getNumber()
                            + "', " + student.getGroupe() + ", '" + student.getSpecial()  + "')";
                    statement.executeUpdate(SQL);
                    SQL = "DELETE FROM APPLICATIONS WHERE NUMBER = '" + student.getNumber() + "';";
                    statement.executeUpdate(SQL);
                }
            }
            connection.close();
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package ru.goose.art.dao;

import org.springframework.stereotype.Component;
import ru.goose.art.models.Person;
import ru.goose.art.models.Student;

import java.sql.*;
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
            for(Student student : students){
                if (Objects.equals(student.getStatus(), "yes")) {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement("INSERT INTO STUDENTS VALUES(?, ?, ?, ?)");
                    preparedStatement.setString(1, student.getName());
                    preparedStatement.setString(2, student.getNumber());
                    preparedStatement.setString(3, student.getGroupe());
                    preparedStatement.setString(4, student.getSpecial());
                    preparedStatement.executeUpdate();
                    preparedStatement =
                            connection.prepareStatement("DELETE FROM APPLICATIONS WHERE NUMBER = ?");
                    preparedStatement.setString(1, student.getNumber());
                    preparedStatement.executeUpdate();
                }
                else if (Objects.equals(student.getStatus(), "stop")){
                    PreparedStatement preparedStatement =
                            connection.prepareStatement("DELETE FROM APPLICATIONS WHERE NUMBER = ?");
                    preparedStatement.setString(1, student.getNumber());
                    preparedStatement.executeUpdate();
                    /*String SQL = "DELETE FROM APPLICATIONS WHERE NUMBER = '" + student.getNumber() + "';";
                    statement.executeUpdate(SQL);*/
                }
            }
            connection.close();
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getAllData() {
        List<Student> students = new ArrayList<Student>();
        connect();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM STUDENTS";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                students.add(new Student(resultSet.getString("name"),
                        resultSet.getString("number"),
                        resultSet.getString("groupe"),
                        resultSet.getString("special"),
                        "yes"));
            }
            connection.close();
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> EditStudents(List<Student> students){
        connect();
        try{
            /*Statement statement = connection.createStatement();
            String SQL = "DELETE FROM STUDENTS;";
            statement.executeUpdate(SQL);*/
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM STUDENTS");
            preparedStatement.executeUpdate();
            for(Student student : students){
                    /*SQL = "INSERT INTO STUDENTS VALUES('" + student.getName() + "', '" + student.getNumber()
                            + "', " + student.getGroupe() + ", '" + student.getSpecial()  + "')";
                    statement.executeUpdate(SQL);*/
                preparedStatement =
                        connection.prepareStatement("INSERT INTO STUDENTS VALUES(?, ?, ?, ?)");
                preparedStatement.setString(1, student.getName());
                preparedStatement.setString(2, student.getNumber());
                preparedStatement.setString(3, student.getGroupe());
                preparedStatement.setString(4, student.getSpecial());
                preparedStatement.executeUpdate();
            }
            connection.close();
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package ru.goose.art.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.goose.art.models.Student;

import java.util.List;
import java.util.Objects;

@Component
public class StudentDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addStudents(List<Student> students){
        for (Student student : students){
            if (Objects.equals(student.getStatus(), "yes")){
                jdbcTemplate.update("INSERT INTO STUDENTS VALUES(?, ?, ?, ?)",
                        student.getName(), student.getNumber(), student.getGroupe(), student.getSpecial());
                jdbcTemplate.update("DELETE FROM APPLICATIONS WHERE NUMBER = ?", student.getNumber());
            }
            else if(Objects.equals(student.getStatus(), "stop")){
                jdbcTemplate.update("DELETE FROM APPLICATIONS WHERE NUMBER = ?", student.getNumber());
            }
        }
    }

    public List<Student> getAllData() {
        return jdbcTemplate.query("SELECT * FROM STUDENTS", new StudentMapper());
    }

    public void EditStudents(List<Student> students){
        jdbcTemplate.update("DELETE FROM STUDENTS");
        for(Student student : students){
            jdbcTemplate.update("INSERT INTO STUDENTS VALUES(?, ?, ?, ?)",
                    student.getName(), student.getNumber(), student.getGroupe(), student.getSpecial());
        }
    }
}

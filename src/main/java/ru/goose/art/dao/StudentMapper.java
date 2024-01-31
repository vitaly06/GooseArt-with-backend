package ru.goose.art.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.goose.art.models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException{
        Student student = new Student();
        student.setName(resultSet.getString("name"));
        student.setNumber(resultSet.getString("number"));
        student.setGroupe(resultSet.getString("groupe"));
        student.setSpecial(resultSet.getString("special"));
        student.setStatus("yes");
        return student;
    }
}

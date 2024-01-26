package ru.goose.art.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.goose.art.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
            /*applications.add(new Person(resultSet.getString("name"),
                    resultSet.getString("number"), "no"));/

             */
        person.setName(resultSet.getString("name"));
        person.setNumber(resultSet.getString("number"));
        person.setStatus("no");
        return person;
    }
}

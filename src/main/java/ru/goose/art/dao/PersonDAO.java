package ru.goose.art.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.goose.art.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Добавление заявки
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO APPLICATIONS VALUES(?, ?)", person.getName(), person.getNumber());
    }

    public List<Person> getAllData() {
        return jdbcTemplate.query("SELECT * FROM APPLICATIONS", new PersonMapper());
    }
}


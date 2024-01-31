package ru.goose.art.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.goose.art.models.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString("name");
    }
}

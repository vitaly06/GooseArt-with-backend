package ru.goose.art.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.goose.art.models.Admin;

@Component
public class AdminDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Вход в панель админа
    public String adminLogin(Admin admin) {
        return jdbcTemplate.query("SELECT NAME FROM ADMINS WHERE LOGIN = ? AND PASSWORD = ?",
                new Object[]{admin.getLogin(), admin.getPassword()}, new AdminMapper())
                .stream().findAny().orElse(null);
    }
}

package com.example.demo.utils;

import com.example.demo.model.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;

/**
 * Маппер сущености пользователя, для преобразования данных БД в сущность
 */
public class UserMaper implements RowMapper<User> {

    /**
     * Отображает строку из ResultSet в объект User.
     *
     * @param  rs     набор результатов для отображения
     * @param  rowNum  текущий номер строки
     * @return       объект User, отображенный из ResultSet
     */
    @Override
    public User mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        return user;
    }
}

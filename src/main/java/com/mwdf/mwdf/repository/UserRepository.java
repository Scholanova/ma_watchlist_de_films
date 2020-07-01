package com.mwdf.mwdf.repository;

import com.mwdf.mwdf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemp;

    public String getUser() {

        Map<String, Object> parameters = new HashMap<>();
        List<User> list = jdbcTemp.query("SELECT NAME as name FROM USERS", parameters, new BeanPropertyRowMapper<>(User.class));

        return list.get(0).getName();
    }

}

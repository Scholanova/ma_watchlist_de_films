package com.mwdf.mwdf.repositories;

import com.mwdf.mwdf.models.CustomList;
import com.mwdf.mwdf.models.User;
import com.mwdf.mwdf.utils.RoleEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomListRepositoryTest {

    @Autowired
    private CustomListRepository customListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    void cleanUp() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "LISTS", "ROLES", "USERS", "MOVIES", "MOVIESLISTS", "USERSLISTS");
    }

    @Nested
    class Test_listAllListsByUser {

        @Test
        void whenUserHaveAList_thenReturnsUsersListsContainThisList() {
            // Given
            User user = new User("dzeq", "azaz", "cy", "ril", Collections.singleton(RoleEnum.USER));
            userRepository.save(user);
            User savedUser = userRepository.findByUsername(user.getUsername());

            CustomList list = new CustomList("maList");
            savedUser.addList(customListRepository.save(list));

            // When
            // Then
            assertThat(customListRepository.findByUsers(savedUser).contains(list));
        }

        @Test
        void whenTwoMessages_thenReturnsListWithTwoMessages() {
            // Given


            // When

            // Then

        }
    }
}

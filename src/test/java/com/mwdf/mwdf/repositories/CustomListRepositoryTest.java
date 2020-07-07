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

import java.util.*;

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
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "ROLES", "USERSLISTS", "MOVIESLISTS", "USERS", "LISTS", "MOVIES");
    }

    @Nested
    class Test_listAllListsByUser {

        @Test
        void whenUserHaveAList_thenReturnsUsersListsContainThisList() {
            // Given
            User user = new User("dzeq", "azaz", "cy", "ril", Collections.singleton(RoleEnum.USER));
            CustomList list = new CustomList("maList");

            list.getUsers().add(user);
            user.getLists().add(list);

            userRepository.save(user);

            // When
            // Then
            assertThat(user.getLists()).contains(list);
            assertThat(customListRepository.findByUsers(user).get(0).getIdList()).isEqualTo(list.getIdList());
            assertThat(customListRepository.findByUsers(user).get(0).getUsers().stream().findFirst().get().getUsername()).contains(user.getUsername());
            assertThat(customListRepository.findByUsers(user).get(0).getTitle()).isEqualTo("maList");
        }

        @Test
        void whenUserWithNoList_returnNoList() {
            // Given
            User user = new User("dzeq", "azaz", "cy", "ril", Collections.singleton(RoleEnum.USER));

            userRepository.save(user);

            // When
            // Then
            assertThat(customListRepository.findByUsers(user)).isEmpty();
        }
    }
}

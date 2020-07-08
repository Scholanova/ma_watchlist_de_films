package com.mwdf.mwdf.repositories;

import com.mwdf.mwdf.models.Comment;
import com.mwdf.mwdf.models.CustomList;
import com.mwdf.mwdf.models.Movie;
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

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private CustomListRepository customListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    void cleanUp() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "ROLES", "USERSLISTS", "MOVIESLISTS", "COMMENTS", "USERS", "LISTS", "MOVIES");
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
            assertThat(userRepository.findByUsername(user.getUsername()).getUsername()).isEqualTo("dzeq");
            assertThat(userRepository.findByUsername(user.getUsername()).getUsername()).isEqualTo("dzeq");
        }

        @Test
        void whenTwoMessages_thenReturnsListWithTwoMessages() {
            // Given
            User user = new User("dzeq", "azaz", "cy", "ril", Collections.singleton(RoleEnum.USER));

            userRepository.save(user);

            // When
            // Then
            assertThat(userRepository.findByUsername(user.getUsername()).getIdUser()).isEqualTo(user.getIdUser());

        }

        @Test
        void whenUser_thenReturnsUsersListsContainThisList() {
            // Given
            User user = new User("dzeq", "azaz", "cy", "ril", Collections.singleton(RoleEnum.USER));
            Movie movie = new Movie(1);
            Movie movie2 = new Movie(2);
            CustomList list = new CustomList("maList");

            list.getUsers().add(user);
            user.getLists().add(list);
            list.getMovies().add(movie);
            list.getMovies().add(movie2);

            Comment comment = new Comment("Super film");
            comment.setUser(user);
            comment.setMovie(movie);

            Comment comment2 = new Comment("Nul film");
            comment2.setUser(user);
            comment2.setMovie(movie2);

            Comment comment3 = new Comment("pas fou film");
            comment3.setUser(user);
            comment3.setMovie(movie);

            user.getComment().add(comment);
            user.getComment().add(comment2);
            user.getComment().add(comment3);
            movie.getComment().add(comment);
            movie.getComment().add(comment3);
            movie2.getComment().add(comment2);

            userRepository.save(user);
            commentRepository.save(comment);

            // When
            // Then
            assertThat(commentRepository.findByUserAndMovie(user, movie2).get(0).getContent()).isEqualTo("Nul film");
            assertThat(commentRepository.findByUserAndMovie(user, movie).size()).isEqualTo(2);
            assertThat(commentRepository.findByUserAndMovie(user, movie2).size()).isEqualTo(1);
        }
    }
}

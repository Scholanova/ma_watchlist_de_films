package com.mwdf.mwdf.controller;


import com.mwdf.mwdf.models.Comment;
import com.mwdf.mwdf.models.CustomList;
import com.mwdf.mwdf.models.Movie;
import com.mwdf.mwdf.models.User;
import com.mwdf.mwdf.repositories.CommentRepository;
import com.mwdf.mwdf.repositories.CustomListRepository;
import com.mwdf.mwdf.repositories.MovieRepository;
import com.mwdf.mwdf.repositories.UserRepository;
import com.mwdf.mwdf.utils.RoleEnum;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class MovieControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate template = new TestRestTemplate();

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private CustomListRepository customListRepository;
    @MockBean
    private MovieRepository movieRepository;
    @MockBean
    private CommentRepository commentRepository;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @AfterEach
    public void reset_mocks() {
        Mockito.reset(userRepository);
        Mockito.reset(commentRepository);
        Mockito.reset(customListRepository);
        Mockito.reset(movieRepository);
    }

    @Nested
    class Test_getMoviesFromIds {

//        @Test
//        @WithMockUser(username = "dzeq", password = "azaz", roles = "USER")
//        void givenUrl_whenCalled_returnmyListTemplate() {
////           given
//            User user = userRepository.findByUsername("dzeq");
//            Movie movie = new Movie(1);
//            Movie movie2 = new Movie(2);
//            CustomList list = new CustomList(1, "maList");
//
//            list.getUsers().add(user);
//            user.getLists().add(list);
//            list.getMovies().add(movie);
//            list.getMovies().add(movie2);
//
//            Comment comment = new Comment("Super film");
//            comment.setUser(user);
//            comment.setMovie(movie);
//
//            Comment comment2 = new Comment("Nul film");
//            comment2.setUser(user);
//            comment2.setMovie(movie2);
//
//            Comment comment3 = new Comment("pas fou film");
//            comment3.setUser(user);
//            comment3.setMovie(movie);
//
//            user.getComment().add(comment);
//            user.getComment().add(comment2);
//            user.getComment().add(comment3);
//            movie.getComment().add(comment);
//            movie.getComment().add(comment3);
//            movie2.getComment().add(comment2);
//
//            userRepository.save(user);
//            commentRepository.save(comment);
//
//            String url = "http://localhost:{port}/list/{listTitle}_{listId}";
//
//            Map<String, String> urlVariables = new HashMap<>();
//            urlVariables.put("port", String.valueOf(port));
//
//
//            urlVariables.put("listTitle", String.valueOf(list.getTitle()));
//            urlVariables.put("listId", String.valueOf(list.getIdList()));
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.TEXT_HTML);
//
//            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
//
////             When
//            ResponseEntity responseEntity = template.exchange(url,
//                    HttpMethod.GET,
//                    httpEntity,
//                    String.class,
//                    urlVariables);
//
////             Then
//            assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
//            assertThat(responseEntity.getBody().toString()).doesNotContain("Votre liste est vide.");
//            assertThat(responseEntity.getBody().toString()).contains("maList");
//        }

    }
}

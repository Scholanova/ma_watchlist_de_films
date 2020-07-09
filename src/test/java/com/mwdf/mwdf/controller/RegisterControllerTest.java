package com.mwdf.mwdf.controller;


import com.mwdf.mwdf.models.User;
import com.mwdf.mwdf.repositories.UserRepository;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RegisterControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate template = new TestRestTemplate();

    @MockBean
    private UserRepository userRepository;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @AfterEach
    public void reset_mocks() {
        Mockito.reset(userRepository);
    }

//    @Nested
//    class Test_POSTRegistred {
//
//        @Test
//        void givenCorrectForm_whenCalled_createUser() throws Exception {
////           given
//            String url = "http://localhost:{port}/inscription";
//
//            Map<String, String> urlVariables = new HashMap<>();
//            urlVariables.put("port", String.valueOf(port));
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            String requestJson = "{" +
//                    "\"userName\":\"dzeq\"," +
//                    "\"password\":\"ble\"," +
//                    "\"firstName\":\"Cyril\"," +
//                    "\"lastName\":\"Gau\"," +
//                    "}";
//            HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, headers);
//
//            User createdUser = new User("dzeq", "ble", "Cyril", "Gau", Collections.singleton(RoleEnum.USER) );
//            when(userRepository.save(userCaptor.capture())).thenReturn(createdUser);
//
////             When
//            ResponseEntity responseEntity = template.exchange(url,
//                    HttpMethod.POST,
//                    httpEntity,
//                    String.class,
//                    urlVariables);
//
////             Then
//            assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
//            assertThat(responseEntity.getBody()).isEqualTo(
//                    "{" +
//                            "\"id\":1," +
//                            "\"name\":\"Flat Nail\"," +
//                            "\"type\":\"Nail\"," +
//                            "\"value\":100," +
//                            "\"storeId\":1" +
//                    "}"
//            );
//        }
//    }

    @Nested
    class Test_GETRegistration {

        @Test
        void givenUrl_whenCalled_returnInscriptionTemplate() {
//           given
            String url = "http://localhost:{port}/inscription";

            Map<String, String> urlVariables = new HashMap<>();
            urlVariables.put("port", String.valueOf(port));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);

            HttpEntity<String> httpEntity = new HttpEntity<>(headers);

//             When
            ResponseEntity responseEntity = template.exchange(url,
                    HttpMethod.GET,
                    httpEntity,
                    String.class,
                    urlVariables);

//             Then
            assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
            Assert.assertTrue(responseEntity.getBody().toString().contains("Pseudo"));
            Assert.assertTrue(responseEntity.getBody().toString().contains("Nom"));
        }

        @Test
        void givenBadForm_whenCalled_returnInscriptionTemplateWithError() {
//           given
            String url = "http://localhost:{port}/inscription-error";

            Map<String, String> urlVariables = new HashMap<>();
            urlVariables.put("port", String.valueOf(port));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);


            HttpEntity<String> httpEntity = new HttpEntity<>(headers);

//             When
            ResponseEntity responseEntity = template.exchange(url,
                    HttpMethod.GET,
                    httpEntity,
                    String.class,
                    urlVariables);

//             Then
            assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
            Assert.assertTrue(responseEntity.getBody().toString().contains("Le nom d'utilisateur existe déjà"));
        }
    }
}

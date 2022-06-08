package ta3ikdb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import ta3ikdb.DTO.AuthRequestDTO;
import ta3ikdb.DTO.OkResponseDTO;
import ta3ikdb.DTO.RegisterRequestDTO;
import ta3ikdb.entities.User;
import ta3ikdb.repositories.UserRepository;

import javax.xml.transform.OutputKeys;
import java.util.Optional;

@TestPropertySource(locations="classpath:application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DbApplication.class)
class AuthTest extends AbstractComponentTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;
    @Test
    void Should_AuthUser_When_RequestValid() {
        Optional<User> user = userRepository.findByMail(mail);
        Assertions.assertFalse(user.isPresent());

        RegisterRequestDTO registerRequestDTO = buildTestRegisterUser();

        sendPostRequest(registerRequestDTO, OkResponseDTO.class, "/authorize/register");

        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().mail(mail).password(password).build();
        OkResponseDTO responseDTO =
                sendPostRequest(authRequestDTO, OkResponseDTO.class, "/authorize/auth");

        Assertions.assertTrue(responseDTO.getSuccess());

    }

    @Test
    void Should_Not_Register_When_UserExist() {
        Optional<User> user = userRepository.findByMail(mail);
        Assertions.assertFalse(user.isPresent());

        RegisterRequestDTO registerRequestDTO = buildTestRegisterUser();

        sendPostRequest(registerRequestDTO, OkResponseDTO.class, "/authorize/register");

        OkResponseDTO responseDTO = sendPostRequest(registerRequestDTO, OkResponseDTO.class, "/authorize/register");
        Assertions.assertFalse(responseDTO.getSuccess());
    }

    @Test
    void Should_Not_Auth_When_UserNotExist() {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().mail(mail).password(password).build();
        OkResponseDTO responseDTO =
                sendPostRequest(authRequestDTO, OkResponseDTO.class, "/authorize/auth");

        Assertions.assertFalse(responseDTO.getSuccess());
    }

    @Test
    void Should_Not_Auth_When_PasswordIncorrect() {

        Optional<User> user = userRepository.findByMail(mail);
        Assertions.assertFalse(user.isPresent());

        RegisterRequestDTO registerRequestDTO = buildTestRegisterUser();

        sendPostRequest(registerRequestDTO, OkResponseDTO.class, "/authorize/register");

        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().mail(randomString()).password(password).build();
        OkResponseDTO responseDTO =
                sendPostRequest(authRequestDTO, OkResponseDTO.class, "/authorize/auth");

        Assertions.assertFalse(responseDTO.getSuccess());
    }
}


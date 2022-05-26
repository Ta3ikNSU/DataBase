package ta3ikdb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import ta3ikdb.DTO.OkResponseDTO;
import ta3ikdb.DTO.RegisterRequestDTO;
import ta3ikdb.entities.User;
import ta3ikdb.repositories.UserRepository;

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
    void Should_RegisterUser_When_RequestValid() {
        Optional<User> user = userRepository.findByMail(mail);
        Assertions.assertFalse(user.isPresent());

        RegisterRequestDTO registerRequestDTO = buildTestRegisterUser();

        OkResponseDTO responseDTO =
                sendPostRequest(registerRequestDTO, OkResponseDTO.class, "/authorize/register");

        user = userRepository.findByMail(mail);
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals(password, user.get().getPassword());
        Assertions.assertTrue(responseDTO.getSuccess());
    }


}


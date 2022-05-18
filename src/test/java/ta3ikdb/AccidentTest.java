package ta3ikdb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DbApplication.class)
class AccidentTest extends AbstractComponentTest {

    @Test
    void Should_GetTop(){

    }
}

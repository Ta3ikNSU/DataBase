package ta3ikdb;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DbApplication.class)
public class UserControllerTest  extends AbstractComponentTest {
}

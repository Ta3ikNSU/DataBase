package ta3ikdb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import ta3ikdb.DTO.RegisterRequestDTO;
import ta3ikdb.entities.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class AbstractComponentTest {
    protected final String mail = "test_mail@mail.com";
    protected final String password = "hard_password12345678";
    private final Logger log = LogManager.getLogger();
    private final List<String> dbList = List.of();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @LocalServerPort
    int port;

    RestTemplate restTemplate = new RestTemplate();


    protected <RequestType, ResponseType> ResponseType sendGetRequest(RequestType request, Class<ResponseType> responseClass, String path) {
        return sendRequest(request, responseClass, HttpMethod.GET, path);
    }

    protected <RequestType, ResponseType> ResponseType sendPutRequest(RequestType request, Class<ResponseType> responseClass, String path) {
        return sendRequest(request, responseClass, HttpMethod.PUT, path);
    }

    protected <RequestType, ResponseType> ResponseType sendDeleteRequest(RequestType request, Class<ResponseType> responseClass, String path) {
        return sendRequest(request, responseClass, HttpMethod.DELETE, path);
    }

    protected <RequestType, ResponseType> ResponseType sendPostRequest(RequestType request, Class<ResponseType> responseClass, String path) {
        return sendRequest(request, responseClass, HttpMethod.POST, path);
    }

    protected <RequestType, ResponseType> ResponseType sendRequest(RequestType request, Class<ResponseType> responseClass, HttpMethod httpMethod, String path) {
        HttpHeaders header = new HttpHeaders();
        return sendRequest(request, responseClass, httpMethod, path, header);
    }

    private <RequestType, ResponseType> ResponseType sendRequest(RequestType request, Class<ResponseType> responseClass, HttpMethod httpMethod, String path, HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<ResponseType> entity;
        try {
            entity = restTemplate.exchange("http://localhost:" + port + path, httpMethod, new HttpEntity<>(request, headers), responseClass);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return entity.getBody();
    }


    @BeforeEach
    @AfterEach
    public void clearDataBase() {
        try {
            String[] types = {"TABLE"};
            ResultSet tables = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().getMetaData().getTables(null, null, "%", types);
            while (tables.next()) {
                log.info("truncate table : {}", tables.getString("TABLE_NAME"));
                jdbcTemplate.execute("truncate table " + tables.getString("TABLE_NAME") + " cascade");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected RegisterRequestDTO buildTestRegisterUser() {
        return RegisterRequestDTO.builder().mail(mail).password(password).build();
    }

    protected Long generateVinNumber() {
        return new Random().nextLong();
    }

    protected String randomString() {
        return UUID.randomUUID().toString();
    }

    protected Car generateTestCar() {
        return Car
                .builder()
                .brand(randomString())
                .model(randomString())
                .vinNumber(generateVinNumber())
                .build();
    }
}

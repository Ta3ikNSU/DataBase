package ta3ikdb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ta3ikdb.DTO.CreateCarAnnouncementsRequestDTO;
import ta3ikdb.DTO.CreateCarAnnouncementsResponseDTO;
import ta3ikdb.DTO.OkResponseDTO;
import ta3ikdb.DTO.RegisterRequestDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DbApplication.class)
public class CarMarketControllerTest  extends AbstractComponentTest {

    private CreateCarAnnouncementsRequestDTO createCarAnnouncementsRequest(String mail){
        return CreateCarAnnouncementsRequestDTO.builder()
                .mail(mail)
                .brand(randomString())
                .model(randomString())
                .transmission(randomString())
                .gear(randomString())
                .engineCapacity(Math.abs(randomInt()))
                .enginePower(Math.abs(randomInt()))
                .color(randomString())
                .mileage(Math.abs(randomInt()))
                .performance(randomString())
                .vinNumber(Math.abs(randomLong()))
                .description(randomString())
                .region(Math.abs(randomInt()))
                .price(Math.abs(randomLong()))
                .build();
    }
    @Test
    void Should_CreateAnnouncement_When_RequestValid(){
        RegisterRequestDTO registerRequestDTO = buildTestRegisterUser();
        sendPostRequest(registerRequestDTO, OkResponseDTO.class, "/authorize/register");

        CreateCarAnnouncementsRequestDTO requestDTO = createCarAnnouncementsRequest(mail);
        CreateCarAnnouncementsResponseDTO responseDTO =
                sendPutRequest(requestDTO, CreateCarAnnouncementsResponseDTO.class,"/car/create");
    }

    @Test
    void Should_CreateAnnouncement_When_AnnouncementExist(){
        RegisterRequestDTO registerRequestDTO = buildTestRegisterUser();
        sendPostRequest(registerRequestDTO, OkResponseDTO.class, "/authorize/register");

        CreateCarAnnouncementsRequestDTO requestDTO = createCarAnnouncementsRequest(mail);
        CreateCarAnnouncementsResponseDTO responseDTO =
                sendPutRequest(requestDTO, CreateCarAnnouncementsResponseDTO.class,"/car/create");

        CreateCarAnnouncementsResponseDTO newResponseDTO =
                sendPutRequest(requestDTO, CreateCarAnnouncementsResponseDTO.class,"/car/create");
    }
    @Test
    void Should_Not_CreateAnnouncement_When_UserNoExist(){

        CreateCarAnnouncementsRequestDTO requestDTO = createCarAnnouncementsRequest(mail);
        CreateCarAnnouncementsResponseDTO responseDTO =
                sendPutRequest(requestDTO, CreateCarAnnouncementsResponseDTO.class,"/car/create");
    }

}

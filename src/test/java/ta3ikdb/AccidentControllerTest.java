package ta3ikdb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ta3ikdb.DTO.AccidentDTO;
import ta3ikdb.DTO.AccidentsDTO;
import ta3ikdb.DTO.OkResponseDTO;
import ta3ikdb.entities.Accident;
import ta3ikdb.entities.Car;
import ta3ikdb.mapper.DTOMapper;
import ta3ikdb.repositories.AccidentRepository;
import ta3ikdb.repositories.CarRepository;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DbApplication.class)
public class AccidentControllerTest extends AbstractComponentTest {

    @Autowired
    AccidentRepository accidentRepository;

    @Autowired
    CarRepository carRepository;

    @Test
    public void Should_AddAccident_When_RequestValid(){
        Car car = generateTestCar();
        carRepository.save(car);
        sendPutRequest(new AccidentDTO(List.of(car.getVinNumber())), OkResponseDTO.class, "/accident");
        List<Accident> accidents = accidentRepository.accidentsByVinNumber(car.getVinNumber());
        Assertions.assertEquals(1, accidents.size());


        Car car2 = generateTestCar();
        carRepository.save(car2);
        sendPutRequest(new AccidentDTO(List.of(car.getVinNumber(), car2.getVinNumber())), OkResponseDTO.class, "/accident");
        List<Accident> accidentsUpdate1 = accidentRepository.accidentsByVinNumber(car.getVinNumber());
        List<Accident> accidentsUpdate2 = accidentRepository.accidentsByVinNumber(car2.getVinNumber());
        Assertions.assertEquals(2, accidentsUpdate1.size());
        Assertions.assertEquals(1, accidentsUpdate2.size());

        AccidentsDTO accidentsDTO = sendPostRequest(null, AccidentsDTO.class, "/accident/" + car.getVinNumber());
        Assertions.assertEquals(accidentsUpdate1.size(), accidentsDTO.getAccidentDTOs().size());
    }
}

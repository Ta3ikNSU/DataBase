package ta3ikdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import ta3ikdb.DTO.AccidentDTO;
import ta3ikdb.DTO.AccidentsDTO;
import ta3ikdb.entities.Accident;
import ta3ikdb.entities.Car;
import ta3ikdb.repositories.AccidentRepository;
import ta3ikdb.repositories.CarRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accident")
public class AccidentController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CarRepository carRepository;

    @Autowired
    AccidentRepository accidentRepository;

    // Рейтинг автомобилей по количеству дтп
    // Критерий Outer Join
    @GetMapping("/top")
    List<Object[]> getAccidentTop() {

        String sql = "select " +
                "c.brand as brand, " +
                "c.model as model, " +
                "count(*) as count " +
                "from car c left outer join accident_cars " +
                "on c.id = accident_cars.cars_id " +
                "group by c.brand, c.model";
        return jdbcTemplate.queryForList(sql).stream().map(row -> {
            Object[] array = new Object[3];
            array[0] = row.get("brand");
            array[1] = row.get("model");
            array[2] = row.get("count");
            return array;
        }).collect(Collectors.toList());
    }

    @PutMapping("")
    void addAccident(@RequestBody AccidentDTO accidentDTO) {
        List<Car> cars = accidentDTO.getVinNumbers().stream().map((vin) -> {
            Optional<Car> optionalCar = carRepository.getCarByVinNumber(vin);
            return optionalCar.orElse(null);
        }).filter(Objects::nonNull).collect(Collectors.toList());
        accidentRepository.save(new Accident(cars));
    }

    @PostMapping("{vin}")
    AccidentsDTO getAccidents(@PathVariable Long vin) {
        return AccidentsDTO
                .builder()
                .accidentDTOs(
                        accidentRepository
                                .accidentsByVinNumber(vin)
                                .stream()
                                .map(
                                        (accident ->
                                                AccidentDTO
                                                        .builder()
                                                        .vinNumbers(accident
                                                                .getCars()
                                                                .stream()
                                                                .map(Car::getVinNumber)
                                                                .collect(Collectors.toList()))
                                                        .build()))
                                .collect(Collectors.toList()))
                .build();
    }
}

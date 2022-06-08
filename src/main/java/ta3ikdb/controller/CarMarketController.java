package ta3ikdb.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import ta3ikdb.DTO.*;
import ta3ikdb.entities.Announcement;
import ta3ikdb.entities.AnnouncementState;
import ta3ikdb.entities.Car;
import ta3ikdb.entities.Profile;
import ta3ikdb.mapper.DTOMapper;
import ta3ikdb.repositories.AnnouncementRepository;
import ta3ikdb.repositories.CarRepository;
import ta3ikdb.repositories.ProfileRepository;
import ta3ikdb.service.AnnouncementFinderService;

import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarMarketController {
    private final Logger log = LogManager.getLogger();

    @Autowired
    AnnouncementFinderService announcementFinderService;

    @Autowired
    CarRepository carRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    // все объявления по фильтру
    @PostMapping("/announcements")
    public CarAnnouncementsResponseDTO getCarAnnouncements(@RequestBody CarAnnouncementsRequestDTO carAnnouncementsRequestDTO) {
        return new CarAnnouncementsResponseDTO(announcementFinderService.getCarsAnnouncementsDTOByCarAnnouncementsRequestDTO(carAnnouncementsRequestDTO, 0));
    }

    // объявление по id
    @PostMapping("/announcements/{id}")
    public CarDTO getCarAnnouncement(@PathVariable Long id) {
        Optional<Car> carOptional = carRepository.getCarByVinNumber(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            return Mappers.getMapper(DTOMapper.class).carToCarDto(car);
        } else {
            return null;
        }
    }

    // создать объявление
    @PutMapping("/create")
    public CreateCarAnnouncementsResponseDTO getCarAnnouncements(@RequestBody CreateCarAnnouncementsRequestDTO createCarAnnouncementsRequestDTO) {

        Integer profile = jdbcTemplate.queryForObject("select count(*) from profile where mail = ?", Integer.class, createCarAnnouncementsRequestDTO.getMail());

        if (profile== null || profile != 1) {
            return new CreateCarAnnouncementsResponseDTO(null);
        }

        Optional<Car> optionalCar = carRepository.findByVinNumberAndAnnouncementStatus(createCarAnnouncementsRequestDTO.getVinNumber(), AnnouncementState.OPEN);
        // Вообще здесь должна быть сложная система с подтверждением через дополнительные документы, но мне лень ...
        Car car;
        Announcement announcement = new Announcement(createCarAnnouncementsRequestDTO.getRegion(), AnnouncementState.OPEN, createCarAnnouncementsRequestDTO.getPrice());
        if (optionalCar.isPresent()) {
            car = optionalCar.get();
            Announcement old = car.getAnnouncement();
            old.setStatus(AnnouncementState.DELETE);
            car.setAnnouncement(announcement);
            car.setPerformance(createCarAnnouncementsRequestDTO.getPerformance());
            car.setColor(createCarAnnouncementsRequestDTO.getColor());
            car.setEngineCapacity(createCarAnnouncementsRequestDTO.getEngineCapacity());
            car.setDescription(createCarAnnouncementsRequestDTO.getDescription());
            car.setGear(createCarAnnouncementsRequestDTO.getGear());
            car.setEnginePower(createCarAnnouncementsRequestDTO.getEnginePower());
        } else {
            car = new Car(announcement, createCarAnnouncementsRequestDTO.getBrand(), createCarAnnouncementsRequestDTO.getModel(), createCarAnnouncementsRequestDTO.getTransmission(), createCarAnnouncementsRequestDTO.getGear(), createCarAnnouncementsRequestDTO.getEngineCapacity(), createCarAnnouncementsRequestDTO.getEnginePower(), createCarAnnouncementsRequestDTO.getColor(), createCarAnnouncementsRequestDTO.getMileage(), createCarAnnouncementsRequestDTO.getPerformance(), createCarAnnouncementsRequestDTO.getVinNumber(), createCarAnnouncementsRequestDTO.getDescription());
            carRepository.save(car);
        }
        announcementRepository.save(announcement);

        Long id = jdbcTemplate.queryForObject("select id from profile where mail = ?", Long.class, createCarAnnouncementsRequestDTO.getMail());

//        jdbcTemplate.update("insert into profile_announcements_car (profile_id, announcements_car_id) values (?,?)", id, car.getAnnouncement().getId());
        return new CreateCarAnnouncementsResponseDTO(Mappers.getMapper(DTOMapper.class).carToCarDto(car));
    }

    // объявления на конкретной странице
    @PostMapping("/announcements/page/{pageNumber}")
    public CarAnnouncementsResponseDTO getCarAnnouncements(@RequestBody CarAnnouncementsRequestDTO carAnnouncementsRequestDTO, @PathVariable Integer pageNumber) {
        return new CarAnnouncementsResponseDTO(announcementFinderService.getCarsAnnouncementsDTOByCarAnnouncementsRequestDTO(carAnnouncementsRequestDTO, pageNumber));
    }

    // количество страниц
    @PostMapping("/announcements/pages")
    public Integer getPageCount(@RequestBody CarAnnouncementsRequestDTO carAnnouncementsRequestDTO) {
        return announcementFinderService.getAnnouncementsPagesCount(carAnnouncementsRequestDTO);
    }



}

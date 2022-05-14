package ta3ikdb.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;
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

    // все объявления по фильтру
    @PostMapping("/announcements")
    public CarAnnouncementsResponseDTO getCarAnnouncements(@RequestBody CarAnnouncementsRequestDTO carAnnouncementsRequestDTO) {
        return new CarAnnouncementsResponseDTO(announcementFinderService.getCarsAnnouncementsDTOByCarAnnouncementsRequestDTO(carAnnouncementsRequestDTO));
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
        Optional<Profile> optionalProfile = profileRepository.findByMail(createCarAnnouncementsRequestDTO.getMail());
        if (optionalProfile.isEmpty()) {
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
            announcementRepository.save(old);
            car.setAnnouncement(announcement);
            car.setPerformance(createCarAnnouncementsRequestDTO.getPerformance());
            car.setColor(createCarAnnouncementsRequestDTO.getColor());
            car.setEngineCapacity(createCarAnnouncementsRequestDTO.getEngineCapacity());
            car.setDescription(createCarAnnouncementsRequestDTO.getDescription());
            car.setGear(createCarAnnouncementsRequestDTO.getGear());
            car.setEnginePower(createCarAnnouncementsRequestDTO.getEnginePower());
            carRepository.save(car);
        } else {
            car = new Car(announcement, createCarAnnouncementsRequestDTO.getBrand(), createCarAnnouncementsRequestDTO.getModel(), createCarAnnouncementsRequestDTO.getTransmission(), createCarAnnouncementsRequestDTO.getGear(), createCarAnnouncementsRequestDTO.getEngineCapacity(), createCarAnnouncementsRequestDTO.getEnginePower(), createCarAnnouncementsRequestDTO.getColor(), createCarAnnouncementsRequestDTO.getMileage(), createCarAnnouncementsRequestDTO.getPerformance(), createCarAnnouncementsRequestDTO.getVinNumber(), createCarAnnouncementsRequestDTO.getDescription());
        }
        announcementRepository.save(announcement);
        Profile profile = optionalProfile.get();
        profile.getFavoriteAnnouncementCar().add(car);
        carRepository.save(car);
        profileRepository.save(profile);
        return new CreateCarAnnouncementsResponseDTO(Mappers.getMapper(DTOMapper.class).carToCarDto(car));
    }

    // объявления на конкретной странице
    @PostMapping("/announcements/page/{pageNumber}")
    public CarAnnouncementsResponseDTO getCarAnnouncements(@RequestBody CarAnnouncementsRequestDTO carAnnouncementsRequestDTO, @PathVariable Integer pageNumber) {
        // TODO()

        return null;
    }

    // количество страниц
    @PostMapping("/announcements/pages")
    public Integer getPageCount(@RequestBody CarAnnouncementsRequestDTO carAnnouncementsRequestDTO) {
        // TODO()

        return 1;
    }


}

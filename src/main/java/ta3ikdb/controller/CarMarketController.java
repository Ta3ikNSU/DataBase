package ta3ikdb.controller;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    private final Logger log =  LogManager.getLogger();

    @Autowired
    AnnouncementFinderService announcementFinderService;

    @Autowired
    CarRepository carRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    AnnouncementRepository announcementRepository;

    @PostMapping("/announcements")
    public CarAnnouncementsResponseDTO getCarAnnouncements(
            @RequestBody CarAnnouncementsRequestDTO carAnnouncementsRequestDTO
    ) {
        log.info("request = {}", carAnnouncementsRequestDTO);
        List<Car> carsNoPrice = announcementFinderService.findCarsByParameters(
                carAnnouncementsRequestDTO.getBrand(),
                carAnnouncementsRequestDTO.getModel(),
                carAnnouncementsRequestDTO.getTransmission(),
                carAnnouncementsRequestDTO.getGear(),
                carAnnouncementsRequestDTO.getMinEngineCapacity(),
                carAnnouncementsRequestDTO.getMaxEngineCapacity(),
                carAnnouncementsRequestDTO.getMinEnginePower(),
                carAnnouncementsRequestDTO.getMaxEnginePower(),
                carAnnouncementsRequestDTO.getColor(),
                carAnnouncementsRequestDTO.getMileage(),
                carAnnouncementsRequestDTO.getPerformance()
        );
        List<Long> ids = carsNoPrice.stream().map(Car::getId).toList();
        List<Car> cars;
        Long left = carAnnouncementsRequestDTO.getMinPrice() == null ? 0 : carAnnouncementsRequestDTO.getMinPrice();
        Long right = carAnnouncementsRequestDTO.getMinPrice() == null ? Long.MAX_VALUE : carAnnouncementsRequestDTO.getMinPrice();
        if (carAnnouncementsRequestDTO.getState() == null) {
            cars = carRepository.findByIdInAndAnnouncement_PriceBetween(
                    ids, left, right
            );
        } else {
            cars = carRepository.findByIdInAndAnnouncement_PriceBetweenAndState(
                    ids, left, right, carAnnouncementsRequestDTO.getState()
            );
        }

        log.info("get cars = {}", cars);
        List<CarDTO> carsDTO = cars.stream().map(car -> Mappers.getMapper(DTOMapper.class).carToCarDto(car)).toList();
        return new CarAnnouncementsResponseDTO(carsDTO);
    }

    @PostMapping("/announcements/{id}")
    public CarDTO getCarAnnouncement(
            @PathVariable Long id
    ) {
        Optional<Car> carOptional = carRepository.getCarByVinNumber(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            return Mappers.getMapper(DTOMapper.class).carToCarDto(car);
        } else {
            return null;
        }
    }

    @PutMapping("/create")
    public CreateCarAnnouncementsResponseDTO getCarAnnouncements(
            @RequestBody CreateCarAnnouncementsRequestDTO createCarAnnouncementsRequestDTO
    ) {
        Optional<Profile> optionalProfile = profileRepository.findByMail(createCarAnnouncementsRequestDTO.getMail());
        if (optionalProfile.isEmpty()) {
            return new CreateCarAnnouncementsResponseDTO(null);
        }

        Optional<Car> optionalCar = carRepository.findByVinNumberAndAnnouncementStatus
                (createCarAnnouncementsRequestDTO.getVinNumber(), AnnouncementState.OPEN);
        // Вообще здесь должна быть сложная система с подтверждением через дополнительные документы, но мне лень ...
        Car car;
        Announcement announcement = new Announcement(
                createCarAnnouncementsRequestDTO.getRegion(),
                AnnouncementState.OPEN,
                createCarAnnouncementsRequestDTO.getPrice()
        );
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
            car = new Car(
                    announcement,
                    createCarAnnouncementsRequestDTO.getBrand(),
                    createCarAnnouncementsRequestDTO.getModel(),
                    createCarAnnouncementsRequestDTO.getTransmission(),
                    createCarAnnouncementsRequestDTO.getGear(),
                    createCarAnnouncementsRequestDTO.getEngineCapacity(),
                    createCarAnnouncementsRequestDTO.getEnginePower(),
                    createCarAnnouncementsRequestDTO.getColor(),
                    createCarAnnouncementsRequestDTO.getMileage(),
                    createCarAnnouncementsRequestDTO.getPerformance(),
                    createCarAnnouncementsRequestDTO.getVinNumber(),
                    createCarAnnouncementsRequestDTO.getDescription()
            );
        }
        announcementRepository.save(announcement);
        Profile profile = optionalProfile.get();
        profile.getFavoriteAnnouncementCar().add(car);
        carRepository.save(car);
        profileRepository.save(profile);
        return new CreateCarAnnouncementsResponseDTO(Mappers.getMapper(DTOMapper.class).carToCarDto(car));
    }

}

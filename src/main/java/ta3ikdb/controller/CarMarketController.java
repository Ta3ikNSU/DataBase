package ta3ikdb.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ta3ikdb.mapper.CarMapper;
import ta3ikdb.DTO.*;
import ta3ikdb.entitys.Announcement;
import ta3ikdb.entitys.AnnouncementState;
import ta3ikdb.entitys.Car;
import ta3ikdb.entitys.Profile;
import ta3ikdb.repositories.CarRepository;
import ta3ikdb.repositories.ProfileRepository;
import ta3ikdb.service.AnnouncementFinderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarMarketController {

    @Autowired
    AnnouncementFinderService announcementFinderService;

    @Autowired
    CarRepository carRepository;

    @Autowired
    ProfileRepository profileRepository;

    @GetMapping("/announcements")
    public CarAnnouncementsResponseDTO getCarAnnouncements(
            @RequestBody CarAnnouncementsRequestDTO carAnnouncementsRequestDTO
    ) {
        List<Car> cars = announcementFinderService.findCarsByParameters(
                carAnnouncementsRequestDTO.brand(),
                carAnnouncementsRequestDTO.model(),
                carAnnouncementsRequestDTO.transmission(),
                carAnnouncementsRequestDTO.gear(),
                carAnnouncementsRequestDTO.minEngineCapacity(),
                carAnnouncementsRequestDTO.maxEngineCapacity(),
                carAnnouncementsRequestDTO.minEnginePower(),
                carAnnouncementsRequestDTO.maxEnginePower(),
                carAnnouncementsRequestDTO.color(),
                carAnnouncementsRequestDTO.mileage(),
                carAnnouncementsRequestDTO.performance()
        );
        return new CarAnnouncementsResponseDTO(cars);
    }

    @GetMapping("/announcements/{id}")
    public CarDTO getCarAnnouncement(
            @PathVariable String id
    ) {
        Optional<Car> carOptional = carRepository.getCarById(Long.valueOf(id));
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            return Mappers.getMapper(CarMapper.class).carToCarDto(car);
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
        if (optionalCar.isPresent()) {
            Car oldCarAnnouncement = optionalCar.get();
            oldCarAnnouncement.getAnnouncement().setStatus(AnnouncementState.DELETE);
            carRepository.save(oldCarAnnouncement);
        }
        Announcement announcement = new Announcement(
                createCarAnnouncementsRequestDTO.getRegion(),
                AnnouncementState.OPEN,
                createCarAnnouncementsRequestDTO.getPrice()
        );
        Car car = new Car(
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

        Profile profile = optionalProfile.get();
        profile.getFavoriteAnnouncementCar().add(car);

        carRepository.save(car);
        profileRepository.save(profile);
        return new CreateCarAnnouncementsResponseDTO(car);
    }

}

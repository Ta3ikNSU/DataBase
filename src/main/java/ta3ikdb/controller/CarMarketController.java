package ta3ikdb.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ta3ikdb.DTO.*;
import ta3ikdb.entities.Announcement;
import ta3ikdb.entities.AnnouncementState;
import ta3ikdb.entities.Car;
import ta3ikdb.entities.Profile;
import ta3ikdb.mapper.CarMapper;
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
//        List<Long> ids = carsNoPrice.stream().map(Car::getId).toList();
//        List<Car> cars = carRepository.findByIdInAndAnnouncement_PriceBetween(
//                ids,
//                carAnnouncementsRequestDTO.getMinPrice(),
//                carAnnouncementsRequestDTO.getMaxPrice()
//                );
        List<CarDTO> carsDTO = carsNoPrice.stream().map(car -> Mappers.getMapper(CarMapper.class).carToCarDto(car)).toList();
        return new CarAnnouncementsResponseDTO(carsDTO);
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
        return new CreateCarAnnouncementsResponseDTO(Mappers.getMapper(CarMapper.class).carToCarDto(car));
    }

}

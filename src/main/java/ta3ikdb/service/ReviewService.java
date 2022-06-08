package ta3ikdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta3ikdb.DTO.AnnouncementDTO;
import ta3ikdb.DTO.CarDTO;
import ta3ikdb.repositories.ReviewRepository;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<CarDTO> getCarsWithOpenAnnouncement() {
        return reviewRepository.getCarsWithOpenAnnouncement()
            .stream()
            .map(car -> new CarDTO(
                    car.getBrand(),
                    car.getModel(),
                    car.getTransmission(),
                    car.getGear(),
                    car.getEngineCapacity(),
                    car.getEnginePower(),
                    car.getColor(),
                    car.getMileage(),
                    car.getPerformance(),
                    car.getVinNumber(),
                    car.getDescription(),
                    new AnnouncementDTO(
                        car.getAnnouncement().getId(),
                        car.getAnnouncement().getRegion(),
                        new SimpleDateFormat("yyyy-MM-dd")
                            .format(car.getAnnouncement().getStart_date().toInstant()),
                        new SimpleDateFormat("yyyy-MM-dd")
                            .format(car.getAnnouncement().getClose_date().toInstant()),
                        car.getAnnouncement().getStatus(),
                        car.getAnnouncement().getPrice(),
                        car.getAnnouncement().getPhotosList()
                    )
                )
            ).collect(Collectors.toList());
    }

}

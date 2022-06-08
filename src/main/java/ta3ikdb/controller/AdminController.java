package ta3ikdb.controller;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ta3ikdb.DTO.CarAnnouncementsResponseDTO;
import ta3ikdb.DTO.ReviewDTO;
import ta3ikdb.DTO.ReviewsResponseDTO;
import ta3ikdb.entities.AnnouncementState;
import ta3ikdb.entities.Review;
import ta3ikdb.mapper.DTOMapper;
import ta3ikdb.repositories.AccidentRepository;
import ta3ikdb.repositories.CarRepository;
import ta3ikdb.repositories.ReviewRepository;
import ta3ikdb.service.ProfileService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    AccidentRepository accidentRepository;

    @Autowired
    ProfileService profileService;


    @PostMapping("/{mail}/cars")
    CarAnnouncementsResponseDTO getCars(@PathVariable String mail) {
        return CarAnnouncementsResponseDTO.builder().announcements(carRepository.findAll().stream().filter(car -> car.getAnnouncement().getStatus() == AnnouncementState.OPEN).map((car -> Mappers.getMapper(DTOMapper.class).carToCarDto(car))).collect(Collectors.toList())).build();
    }

    @PostMapping("/{mail}/reviews")
    ReviewsResponseDTO getReviews(@PathVariable String mail) {
        return ReviewsResponseDTO.builder().reviewDTOList(reviewRepository.findAll().stream().map(review -> ReviewDTO.builder().description(review.getDescription()).id(review.getId()).build()).collect(Collectors.toList())).build();
    }

    @DeleteMapping("/{mail}/cars/{id}")
    void deleteCarAnn(@PathVariable String mail, @PathVariable Long id) {
        profileService.updateAnnouncementStateById(id, AnnouncementState.DELETE);
    }

    @PostMapping("/{mail}/review")
    void updateReview(@PathVariable String mail, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findReviewById(reviewDTO.getId()).get();
        review.setDescription(reviewDTO.getDescription());
    }

    @DeleteMapping("/{mail}/review")
    void deleteReview(@PathVariable String mail, ReviewDTO reviewDTO) {
        reviewRepository.deleteByIdEquals(reviewDTO.getId());
    }
}

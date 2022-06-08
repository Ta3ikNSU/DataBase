package ta3ikdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ta3ikdb.DTO.CarDTO;
import ta3ikdb.DTO.ReviewsResponseDTO;
import ta3ikdb.service.ReviewService;

import java.util.List;

@RestController(value = "/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ReviewsResponseDTO getReviewsByCarParams() {
        return null;
    }

    @GetMapping(value = "/getCarsWithOpenAnnouncement")
    public List<CarDTO> getCarsWithOpenAnnouncement() {
        return reviewService.getCarsWithOpenAnnouncement();
    }

}

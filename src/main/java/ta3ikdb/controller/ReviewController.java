package ta3ikdb.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ta3ikdb.DTO.ReviewsResponseDTO;

@RestController(value = "/review")
public class ReviewController {

    @PostMapping
    public ReviewsResponseDTO getReviewsByCarParams() {
        return null;
    }
}

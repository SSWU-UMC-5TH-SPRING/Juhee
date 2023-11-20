package umc.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    @PostMapping("/add/{storeIdx}")
    public ApiResponse<ReviewResponseDTO.AddReviewResultDTO> add(@PathVariable Long storeIdx, @RequestBody ReviewRequestDTO.AddReviewDto request) {
        Review review = reviewCommandService.addReview(storeIdx, request);
        return ApiResponse.onSuccess(ReviewConverter.toAddResultDTO(review));
    }
}

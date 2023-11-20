package umc.spring.converter;

import java.time.LocalDateTime;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

public class ReviewConverter {

    public static ReviewResponseDTO.AddReviewResultDTO toAddResultDTO(Review review) {
        return ReviewResponseDTO.AddReviewResultDTO.builder()
                .reviewIdx(review.getReviewIdx())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(Store storeIdx, ReviewRequestDTO.AddReviewDto request) {
        return Review.builder()
                .content(request.getContent())
                .reviewImg(request.getReviewImg())
                .rating(request.getRating())
                .store(storeIdx)
                .build();
    }
}

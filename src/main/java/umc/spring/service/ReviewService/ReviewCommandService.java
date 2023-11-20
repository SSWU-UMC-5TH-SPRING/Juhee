package umc.spring.service.ReviewService;

import umc.spring.domain.Review;
import umc.spring.web.dto.review.ReviewRequestDTO;

public interface ReviewCommandService {
    Review addReview(Long storeIdx, ReviewRequestDTO.AddReviewDto request);
}

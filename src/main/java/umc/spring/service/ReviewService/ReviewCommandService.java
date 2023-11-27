package umc.spring.service.ReviewService;

import java.util.Optional;
import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.review.ReviewRequestDTO;

public interface ReviewCommandService {
    Review addReview(Long storeIdx, Long userIdx, ReviewRequestDTO.AddReviewDto request);
    Page<Review> getMyReviewList(Long userIdx, Optional<Store> store, Integer page);
}

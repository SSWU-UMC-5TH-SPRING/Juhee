package umc.spring.service.ReviewService;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.handler.FoodCategoryHandler;
import umc.spring.handler.StoreHandler;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Review addReview(Long storeIdx, ReviewRequestDTO.AddReviewDto request) {
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new StoreHandler(
                        ErrorStatus.STORE_NOT_FOUND));
        Review newReview = ReviewConverter.toReview(store, request);

        return reviewRepository.save(newReview);
    }
}

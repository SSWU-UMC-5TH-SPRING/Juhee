package umc.spring.service.ReviewService;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.handler.StoreHandler;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.repository.UserRepository;
import umc.spring.validation.validator.StoreExistValidator;
import umc.spring.web.dto.review.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final StoreExistValidator storeExistValidator;

    @Override
    @Transactional
    public Review addReview(Long storeIdx, Long userIdx, ReviewRequestDTO.AddReviewDto request) {
            storeExistValidator.isValid(storeIdx, null);
            Store store = storeRepository.findById(storeIdx)
                    .orElseThrow(() -> new StoreHandler(
                            ErrorStatus.STORE_NOT_FOUND));
            User user = userRepository.findById(userIdx)
                    .orElseThrow(() -> new StoreHandler(
                            ErrorStatus.USER_NOT_FOUND
                    ));
            Review newReview = ReviewConverter.toReview(store, user, request);

            return reviewRepository.save(newReview);
    }

    @Override
    public Page<Review> getMyReviewList(Long userIdx, Optional<Store> store, Integer page) {
        User user = userRepository.getReferenceById(userIdx);
        Store storeEntity = store.orElse(null);
        Page<Review> ReviewPage = reviewRepository.findAllByStoreAndUser(storeEntity, user, PageRequest.of(page, 10));
        System.out.println("출력" + ReviewPage);
        return ReviewPage;
    }
}

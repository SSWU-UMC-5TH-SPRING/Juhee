package umc.spring.converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;

public class ReviewConverter {

    public static ReviewResponseDTO.AddReviewResultDTO toAddResultDTO(Review review) {
        return ReviewResponseDTO.AddReviewResultDTO.builder()
                .reviewIdx(review.getReviewIdx())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(Store store, User user, ReviewRequestDTO.AddReviewDto request) {
        return Review.builder()
                .content(request.getContent())
                .reviewImg(request.getReviewImg())
                .rating(request.getRating())
                .store(store)
                .user(user)
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getName())
                .score(review.getRating())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getContent())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList) {
        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreViewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static ReviewResponseDTO.MyReviewPreviewDTO myReviewDTO(User user, Review review) {
        return ReviewResponseDTO.MyReviewPreviewDTO.builder()
                .nickName(user.getName())
                .content(review.getContent())
                .rating(review.getRating())
                .ownerComment(review.getReplies().getContent())
                .ownerCommentDate(review.getReplies().getCreatedAt())
                .build();
    }

    public static ReviewResponseDTO.ReviewImgDTO myReviewImgDTO(Review review) {
        return ReviewResponseDTO.ReviewImgDTO.builder()
                .ImgUrl(review.getReviewImg())
                .build();
    }

    public static ReviewResponseDTO.MyReviewPreviewDTO myReviewImgListDTO (Page<Review> reviewImgList, Review review) {
        List<ReviewResponseDTO.ReviewImgDTO> myReviewImgList = reviewImgList.stream()
                .map(ReviewConverter::myReviewImgDTO).collect(Collectors.toList());

        return ReviewResponseDTO.MyReviewPreviewDTO.builder()
                .nickName(review.getUser().getName())
                .content(review.getContent())
                .rating(review.getRating())
                .ownerComment(review.getReplies().getContent())
                .ownerCommentDate(review.getReplies().getCreatedAt())
                .imgList(myReviewImgList)
                .build();
    }

    public static ReviewResponseDTO.MyReviewPreViewListDTO myReviewPreViewListDTO(Page<Review> myReviewList, Optional<Store> store) {
        List<ReviewResponseDTO.MyReviewPreviewDTO> myReviewPreviewDTOList = myReviewList.stream()
                .map(review -> myReviewDTO(review.getUser(), review)).collect(Collectors.toList());

        return ReviewResponseDTO.MyReviewPreViewListDTO.builder()
                .storeName(store.get().getStoreIdx())
                .reviewList(myReviewPreviewDTOList)
                .build();
    }
}

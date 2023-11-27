package umc.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.ExistStores;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/add/{storeIdx}/{userIdx}")
    public ApiResponse<ReviewResponseDTO.AddReviewResultDTO> add(@PathVariable Long storeIdx, @PathVariable Long userIdx, @RequestBody ReviewRequestDTO.AddReviewDto request) {
        Review review = reviewCommandService.addReview(storeIdx, userIdx, request);
        return ApiResponse.onSuccess(ReviewConverter.toAddResultDTO(review));
    }

    @GetMapping("/{storeIdx}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeIdx", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStores @PathVariable(name = "storeIdx") Long storeIdx, @RequestParam(name = "page") Integer page) {
        reviewQueryService.getReviewList(storeIdx, page);
        return null;
    }

    @GetMapping("/{userIdx}/{storeIdx}")
    public ApiResponse<ReviewResponseDTO.MyReviewPreViewListDTO> getMyReviewList(@PathVariable Long userIdx, @PathVariable Long storeIdx, @RequestParam(name = "page") Integer page) {
        Optional<Store> store = storeQueryService.findStore(storeIdx);
        Page<Review> review = reviewCommandService.getMyReviewList(userIdx, store, page);
        return ApiResponse.onSuccess(ReviewConverter.myReviewPreViewListDTO(review, store));
    }
}

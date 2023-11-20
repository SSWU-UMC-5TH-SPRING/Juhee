package umc.spring.web.dto.review;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class AddReviewDto{
        @NotBlank
        String content;
        @NotNull
        Integer rating;
        @NotBlank
        String reviewImg;
    }
}

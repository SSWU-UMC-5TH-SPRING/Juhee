package umc.spring.web.dto.store;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import umc.spring.domain.enums.StoreCategory;
import umc.spring.validation.annotation.ExistRegion;

public class StoreRequestDTO {
    @Getter
    public static class AddRegionDto{
        @ApiModelProperty(value = "가게 카테고리", allowableValues = "KOREAN, JAPANESE, CHINESE", required = true)
        StoreCategory category;
        @NotBlank
        String location;
        @ExistRegion
        Long region;
    }
}

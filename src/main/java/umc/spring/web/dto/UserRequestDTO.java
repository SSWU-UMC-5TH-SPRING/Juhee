package umc.spring.web.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

public class UserRequestDTO {

    @Getter
    public static class JoinDto{
        @NotNull
        Integer gender;
        @NotBlank
        String name;
        @Size(min = 5, max = 12)
        String birth;
        @NotNull
        String address;
        @NotNull
        String profileImg;
        @ExistCategories
        List<Long> userPreferFood;
    }
}

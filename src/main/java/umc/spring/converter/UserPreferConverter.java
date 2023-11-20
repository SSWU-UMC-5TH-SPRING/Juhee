package umc.spring.converter;

import java.util.List;
import java.util.stream.Collectors;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.mapping.UserPrefer;

public class UserPreferConverter {
    public static List<UserPrefer> toUserPreferList(List<FoodCategory> foodCategoryList) {

        return foodCategoryList.stream()
                .map(foodCategory ->
                        UserPrefer.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}

package umc.spring.service.FoodCategoryService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.FoodCategoryRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodCategoryQueryServiceImpl implements FoodCategoryQueryService{

    private final FoodCategoryRepository foodCategoryRepository;
    @Override
    public Long findFoodCategories(List<Long> categories) {
        return categories.stream()
                .filter(foodCategoryRepository::existsById)
                .count();
    }
}

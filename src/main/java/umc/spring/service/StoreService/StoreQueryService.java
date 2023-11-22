package umc.spring.service.StoreService;

import java.util.Optional;
import umc.spring.domain.Store;

public interface StoreQueryService {
    Optional<Store> findStore(Long storeIdx);
}

package umc.spring.converter;

import java.time.LocalDateTime;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

public class StoreConverter {

    public static StoreResponseDTO.AddRegionDTO toAddResultDTO(Store store) {
        return StoreResponseDTO.AddRegionDTO.builder()
                .storeIdx(store.getStoreIdx())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.AddRegionDto request, Region region) {
        return Store.builder()
                .region(region)
                .location(request.getLocation())
                .category(request.getCategory())
                .build();
    }
}

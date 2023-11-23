package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.store.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService{

    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;
    @Override
    @Transactional
    public Store addRegion(StoreRequestDTO.AddRegionDto request) {
        Region region = regionRepository.getReferenceById(request.getRegion());
        Store store = StoreConverter.toStore(request, region);

        return storeRepository.save(store);
    }
}

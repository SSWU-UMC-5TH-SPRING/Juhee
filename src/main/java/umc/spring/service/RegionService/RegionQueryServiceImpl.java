package umc.spring.service.RegionService;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Region;
import umc.spring.repository.RegionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionQueryServiceImpl implements RegionQueryService{
    private final RegionRepository regionRepository;

    @Override
    public Optional<Region> findRegion(Long regionIdx) {
        return regionRepository.findById(regionIdx);
    }
}

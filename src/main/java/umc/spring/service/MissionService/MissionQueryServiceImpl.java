package umc.spring.service.MissionService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.UserMission;
import umc.spring.handler.StoreHandler;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.repository.UserMissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final UserMissionRepository userMissionRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public UserMission findUserMission(Long userMissionIdx) {
        return userMissionRepository.getReferenceById(userMissionIdx);
    }

    @Override
    public Page<Mission> getMissionList(Long storeIdx, Integer page) {
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new StoreHandler(
                        ErrorStatus.STORE_NOT_FOUND
                ));
        Page<Mission> missionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        //List<Mission> missionList = missionRepository.findAllByStore(store);

        return missionPage;
    }
}

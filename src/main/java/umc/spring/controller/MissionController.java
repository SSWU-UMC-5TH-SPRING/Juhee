package umc.spring.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.ExistStores;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/add/{storeIdx}")
    public ApiResponse<MissionResponseDTO.AddMissionResultDTO> add(@RequestBody @Valid MissionRequestDTO.AddMissionDto request, @ExistStores @PathVariable(name = "storeIdx") Long storeIdx) {
        Mission mission = missionCommandService.addMission(storeIdx, request);
        return ApiResponse.onSuccess(MissionConverter.toAddResultDTO(mission));
    }
}

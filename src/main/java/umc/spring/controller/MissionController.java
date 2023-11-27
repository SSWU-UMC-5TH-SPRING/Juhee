package umc.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.UserMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.ExistStores;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.validation.annotation.ExistUserMission;
import umc.spring.validation.annotation.PageLessNull;
import umc.spring.validation.annotation.StatusMission;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;
import umc.spring.web.dto.mission.userMission.UserMissionResponseDTO;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/add/{storeIdx}")
    public ApiResponse<MissionResponseDTO.AddMissionResultDTO> add(@RequestBody @Valid MissionRequestDTO.AddMissionDto request, @ExistStores @PathVariable(name = "storeIdx") Long storeIdx) {
        Mission mission = missionCommandService.addMission(storeIdx, request);
        return ApiResponse.onSuccess(MissionConverter.toAddResultDTO(mission));
    }

    @PostMapping("/challenge/{missionIdx}/{userIdx}")
    public ApiResponse<UserMissionResponseDTO.ChallengeMissionResultDTO> challengeMission(@StatusMission @PathVariable(name = "missionIdx") Long missionIdx, @ExistUser @PathVariable(name = "userIdx") Long userIdx) {
        UserMission userMission = missionCommandService.challengeMission(missionIdx, userIdx);
        return ApiResponse.onSuccess(MissionConverter.toChallengeResultDTO(userMission));
    }

    @GetMapping("/{storeIdx}")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE4001", description = "해당하는 가게가 없습니다.")
    })
    @Parameters({
            @Parameter(name = "storeIdx", description = "가게의 아이디 path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<List<MissionResponseDTO.MissionListResultDTO>> getMissionList(@ExistStores @PathVariable(name = "storeIdx") Long storeIdx, @PageLessNull @RequestParam(name = "page") Integer page) {
        Page<Mission> mission = missionQueryService.getMissionList(storeIdx, page);
        return ApiResponse.onSuccess(MissionConverter.getMissionResultListDTO(mission));
    }

    @GetMapping("/mymission/{userIdx}")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "내가 진행중인 미션 목록을 조회하는 API 이며, 페이징을 포함합니다. query String으로 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "해당하는 유저가 없습니다.")
    })
    @Parameters({
            @Parameter(name = "userIdx", description = "유저의 아이디 path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<List<MissionResponseDTO.InisProgressMissionDTO>> getInisProgressMissionList(@ExistUser @PathVariable(name = "userIdx") Long userIdx, @PageLessNull @RequestParam(name = "page") Integer page) {
        Page<UserMission> mission = missionQueryService.getInisProgressMissionList(userIdx, page);
        return ApiResponse.onSuccess(MissionConverter.getInisProgressMissionListDTO(mission));
    }

    @PostMapping("/success/{userMissionIdx}")
    @Operation(summary = "진행 중인 미션 진행 완료로 바꾸기 API", description = "진행 중인 미션의 상태를 성공으로 변경하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4002", description = "해당하는 미션이 없습니다.")
    })
    @Parameters({
            @Parameter(name = "userMissionIdx", description = "해당 미션의 아이디 path variable 입니다"),
    })
    public ApiResponse<UserMissionResponseDTO.UserMissionResultDTO> changeStatus(@ExistUserMission @PathVariable(name = "userMissionIdx") Long userMissionIdx) {
        UserMission userMission = missionCommandService.changeUserMissionStatus(userMissionIdx);
        return ApiResponse.onSuccess(MissionConverter.changeUserMission(userMission));
    }
}

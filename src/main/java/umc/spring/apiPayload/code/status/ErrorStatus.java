package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관련 응답

    // ~~~ 관련 응답 ....

    // For Test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),

    // FoodCategory
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FC4001", "음식 카테고리가 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "해당하는 가게가 없습니다."),
    //user
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER4001", "해당하는 유저가 없습니다."),
    // mission
    INPROGRESS_MISSION(HttpStatus.MULTI_STATUS, "MISSION4001", "이미 진행중인 미션입니다."),

    //region
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION4001", "해당하는 지역이 없습니다");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}

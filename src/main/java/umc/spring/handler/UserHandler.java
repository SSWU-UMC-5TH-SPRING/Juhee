package umc.spring.handler;

import umc.spring.apiPayload.GeneralException;
import umc.spring.apiPayload.code.BaseErrorCode;

public class UserHandler extends GeneralException {
    public UserHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

package umc.spring.handler;

import umc.spring.apiPayload.GeneralException;
import umc.spring.apiPayload.code.BaseErrorCode;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

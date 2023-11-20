package umc.spring.handler;

import umc.spring.apiPayload.GeneralException;
import umc.spring.apiPayload.code.BaseErrorCode;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

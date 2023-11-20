package umc.spring.handler;

import umc.spring.apiPayload.GeneralException;
import umc.spring.apiPayload.code.BaseErrorCode;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

package com.iam.chobo.domain.board.exception;

import com.iam.chobo.global.error.ErrorCode;
import com.iam.chobo.global.error.BusinessException;

public class NotAllowException extends BusinessException {
    /**
     *
     */
    private static final long serialVersionUID = 6028953352970261384L;

    public NotAllowException(){
        super(ErrorCode.HANDLE_ACCESS_DENIED);
    }
}

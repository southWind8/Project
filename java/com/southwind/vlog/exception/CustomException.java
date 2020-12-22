package com.southwind.vlog.exception;

import com.southwind.vlog.common.ResultCode;

/**
 * @ClassName CustomException
 * @Description TODO
 * @Author 南风
 * @Date 2020/12/19
 **/

public class CustomException extends RuntimeException{
    protected ResultCode resultCode;

    public CustomException(String msg, ResultCode resultCode) {
        super(msg);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}

package com.waltcow.configuration;

import com.waltcow.utility.HttpResponseEnum;
import com.waltcow.utility.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author waltcow
 * @Created 2017/11/8
 */
@Slf4j
@ControllerAdvice
public class GeneralExceptionHandler {

    @ResponseBody
    @ExceptionHandler
    public WebResult handleException(Throwable e) {
        if(e.getClass().getName().contains("MethodArgumentTypeMismatchException")){
            return WebResult.failure(HttpResponseEnum.ARGUMENT_NOT_MATCH.getCode(),
                    HttpResponseEnum.ARGUMENT_NOT_MATCH.getMsg());
        }
        if(e.getClass().getName().contains("AccessDeniedException")){
            return WebResult.failure(HttpResponseEnum.USER_ACCESS_DENIED.getCode(),
                    HttpResponseEnum.USER_ACCESS_DENIED.getMsg());
        }
        log.error(e.getMessage(), e);
        return WebResult.error(HttpResponseEnum.SC_INTERNAL_SERVER_ERROR.getCode(), HttpResponseEnum
                .SC_INTERNAL_SERVER_ERROR.getMsg(), e.getMessage());
    }

}

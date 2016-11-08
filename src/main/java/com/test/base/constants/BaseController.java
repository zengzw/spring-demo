package com.test.base.constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.test.base.ResponseMessage;
import com.test.base.exception.BusinessException;
import com.test.base.exception.BusinessRuntimeException;

/**
 * 所有 Controller类请继承这个类
 * 
 * @author sunkey
 * @date Mar 15, 2013 6:20:19 PM
 * @version 1.0.0
 * @copyright fpx.com
 */
public abstract class BaseController extends com.test.base.BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 业务运行时异常
     * 
     * @param request 请求
     * @param ex BusinessRuntimeException异常
     * @return Object
     */
    @ExceptionHandler(BusinessRuntimeException.class)
    public Object handleBusinessRuntimeException(HttpServletRequest request, HttpServletResponse response,
            BusinessRuntimeException ex) {
        return handleResponse(request, response, ex.getErrCode(), ex, ex.getParams());
    }

    /**
     * 业务异常
     * 
     * @param request 请求
     * @param ex BusinessException异常
     * @return Object
     */
    @ExceptionHandler(BusinessException.class)
    public Object handleBusinessException(HttpServletRequest request, HttpServletResponse response, BusinessException ex) {
        return handleResponse(request, response, ex.getErrCode(), ex, ex.getParams());
    }

    @Override
    public Object handleThrowable(HttpServletRequest request, HttpServletResponse response, Throwable ex,
            boolean isResponseMessage) {
        logger.error("页面错误: " + ex.getMessage(), ex);
         request.setAttribute("isResponseMessage", isResponseMessage);
        if (ex instanceof BusinessRuntimeException) {
            return handleBusinessRuntimeException(request, response, (BusinessRuntimeException) ex);
        } else if (ex instanceof BusinessException) {
            return handleBusinessException(request, response, (BusinessException) ex);
        }
        return super.handleThrowable(request, response, ex, isResponseMessage);
    }

    /**
     * 只是返回成功响应，不用国际化
     * 
     * @return response message 对象
     */
    protected ResponseMessage successResponse() {
        ResponseMessage message = new ResponseMessage();
        return message;
    }

}

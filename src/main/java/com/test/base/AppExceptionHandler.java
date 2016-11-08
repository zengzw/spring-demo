package com.test.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.base.constants.ServiceException;
import com.test.commom.web.source.service.IMessageSource;

/**
 * 异常处理类
 * 
 * @author sunkey
 * @date Mar 15, 2013 6:20:19 PM
 * @version 1.0.0
 * @copyright fpx.com
 */
public abstract class AppExceptionHandler implements IMessageSource {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AppExceptionHandler.class);

    /**
     * 数据格式请求不符合json格式异常返回的错误信息
     * 
     * @param request 请求
     * @param ex HttpMessageNotReadableException异常
     * @return Object
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object handleHttpMessageNotReadableException(HttpServletRequest request, HttpServletResponse response,
            HttpMessageNotReadableException ex) {
        return handleResponse(request, response, SystemMessageCode.HTTP_MESSAGE_NOT_READABLE, ex);
    }

  /*  *//**
     * 数据库异常返回的错误信息
     * 
     * @param request 请求
     * @param ex DuplicateKeyException异常
     * @return Object
     *//*
    @ExceptionHandler(DuplicateKeyException.class)
    public Object handleDuplicateKeyException(HttpServletRequest request, HttpServletResponse response,
            DuplicateKeyException ex) {
        return handleResponse(request, response, SystemMessageCode.DUPLICATE_DATA, ex);
    }*/

    /**
     * content type不支持
     * 
     * @param request 请求
     * @param ex HttpMediaTypeNotSupportedException异常
     * @return Object
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Object handleHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpServletResponse response,
            HttpMediaTypeNotSupportedException ex) {
        return handleResponse(request, response, SystemMessageCode.NOT_SUPPORTED_MEDIA_TYPE, ex, ex.getContentType()
                .getType());
    }

    /**
     * 数据绑定错误
     * 
     * @param request 请求
     * @param ex 异常
     * @return Object
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    public Object handleServletRequestBindingException(HttpServletRequest request, HttpServletResponse response,
            ServletRequestBindingException ex) {
        LOGGER.error("后台错误: " + ex.getMessage(), ex);
        ResponseMessage message = new ResponseMessage(getMessageSourceService());
        message.addError(SystemMessageCode.DATA_BIND_ERROR);
        return resolveResponse(request, response, message);
    }

    /**
     * 绑定错误
     * 
     * @param request 请求
     * @param ex 异常
     * @return Object
     */
    @ExceptionHandler(BindException.class)
    public Object handleBindException(HttpServletRequest request, HttpServletResponse response, BindException ex) {
        LOGGER.error("后台错误: " + ex.getMessage(), ex);
        ResponseMessage message = new ResponseMessage(getMessageSourceService());
        List<ObjectError> errors = ex.getAllErrors();
        for (ObjectError e : errors) {
            message.addErrorWithMessage(e.getCode(), e.getDefaultMessage());
        }
        return resolveResponse(request, response, message);
    }

    /**
     * 业务逻辑处理异常ServiceException返回的错误信息
     * 
     * @param request 请求
     * @param ex 异常
     * @return Object
     */
    @ExceptionHandler(ServiceException.class)
    public Object handleServiceException(HttpServletRequest request, HttpServletResponse response, ServiceException ex) {

        if (StringUtils.isNotBlank(ex.getCode())) {
            return handleResponse(request, response, ex.getCode(), ex, ex.getArgs());
        }
        return handleResponse(request, response, ex.getMessage(), ex);
    }

    /**
     * 内部错误超类异常Exception返回的错误信息
     * 
     * @param request 请求
     * @param ex 异常
     * @return Object
     */
    @ExceptionHandler(Exception.class)
    public Object handleException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return handleResponse(request, response, SystemMessageCode.ERROR, ex);
    }

    /**
     * 处理单个错误
     * 
     * @param request 请求
     * @param errorCode 错误码
     * @param t 异常
     * @param args 参数
     * @return 响应对象
     */
    protected Object handleResponse(HttpServletRequest request, HttpServletResponse response, String errorCode,
            Throwable t, Object... args) {
        LOGGER.error(t.getMessage(), t);
        ResponseMessage handleSingleError = handleSingleError(request, response, errorCode, t, args);
        return resolveResponse(request, response, handleSingleError);
    }

    /**
     * 将结果转化为ModelAndView
     * 
     * @param request 请求
     * @param result 响应的错误信息
     * @return ModelAndView
     */
    protected Object resolveResponse(HttpServletRequest request, HttpServletResponse response, ResponseMessage result) {
        Object isResponseMessage = request.getAttribute("isResponseMessage");
        if (isResponseMessage != null && Boolean.valueOf(isResponseMessage.toString()) == true) {
            return result;
        }
        boolean isJsonExpected = isJsonExpected(request);
        if (isJsonExpected){
          
            String json =  JSON.toJSONString(result);
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                response.addHeader("content-type", MediaType.APPLICATION_JSON_VALUE);
                writer.write(json);
                writer.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
            return null;
        } else {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("errors/500");
            List<ResponseError> failures = result.getFailures();
            List<String> list = new ArrayList<String>();
            for (ResponseError re : failures) {
                String errorMessage = re.getErrorMessage();
                list.add(errorMessage);
            }
            mv.addObject("errors", list);
            return mv;
        }
    }

    /**
     * 是否为json响应
     * 
     * @param request 请求
     * @return 是/否
     */
    protected boolean isJsonExpected(HttpServletRequest request) {

        String accpect = request.getHeader("Accept");
        if (StringUtils.isNotBlank(accpect)) {
            return accpect.contains(MediaType.APPLICATION_JSON_VALUE);
        }

        String contentType = request.getHeader("Content-Type");
        if (StringUtils.isNotBlank(contentType)) {
            return contentType.contains(MediaType.APPLICATION_JSON_VALUE);
        }
        return false;
    }

    /**
     * 处理单个错误
     * 
     * @param request 请求
     * @param errorCode 错误码
     * @param t 异常
     * @param args 参数
     * @return 响应对象
     */
    private ResponseMessage handleSingleError(HttpServletRequest request, HttpServletResponse response,
            String errorCode, Throwable t, Object... args) {
        ResponseMessage responseMessage = new ResponseMessage(getMessageSourceService());
        responseMessage.addError(errorCode, args);
       System.out.println(">>> "+responseMessage.getMessage());
        return responseMessage;
    }

    /**
     * 处理异常
     * 
     * @param request 请求
     * @param ex 异常
     * @param isResponseMessage true返回ResponseMessage , false返回ModelView
     * @return 处理结果
     */
    public Object handleThrowable(HttpServletRequest request, HttpServletResponse response, Throwable ex,
            boolean isResponseMessage) {
        LOGGER.error("页面错误: " + ex.getMessage(), ex);
        request.setAttribute("isResponseMessage", isResponseMessage);
        if (ex instanceof HttpMessageNotReadableException) {
            return handleHttpMessageNotReadableException(request, response, (HttpMessageNotReadableException) ex);
      /*  } else if (ex instanceof DuplicateKeyException) {
            return handleDuplicateKeyException(request, response, (DuplicateKeyException) ex);*/
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            return handleHttpMediaTypeNotSupportedException(request, response, (HttpMediaTypeNotSupportedException) ex);
        } else if (ex instanceof BindException) {
            return handleBindException(request, response, (BindException) ex);
        } else if (ex instanceof ServiceException) {
            return handleServiceException(request, response, (ServiceException) ex);
        } else if (ex instanceof Exception) {
            return handleException(request, response, null, (Exception) ex);
        } else {
            return handleSingleError(request, response, SystemMessageCode.ERROR, ex);
        }
    }

    /**
     * 得到错误对象
     * 
     * @param request
     * @param response
     * @param ex
     * @return
     */
    public ResponseError getResponseError(HttpServletRequest request, HttpServletResponse response, Throwable ex) {
        Object obj = handleThrowable(request, response, ex, true);
        ResponseMessage message = (ResponseMessage) obj;
        List<ResponseError> errors = message.getFailures();
        if (!errors.isEmpty()) {
            return errors.get(0);
        }
        return null;
    }

}

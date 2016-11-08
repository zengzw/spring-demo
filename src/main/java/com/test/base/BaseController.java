package com.test.base;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.test.commom.web.source.service.IMessageSourceService;

/**
 * 所有 Controller类请继承这个类
 * 
 * @author sunkey
 * @date Mar 15, 2013 6:20:19 PM
 * @version 1.0.0
 * @copyright fpx.com
 */
public abstract class BaseController extends AppExceptionHandler {


    @Resource
    private IMessageSourceService messageSourceService;

    @Resource
    private LocalValidatorFactoryBean validator;

    private boolean isNotTest = true;

    /**
     * 设置请求上下文参数
     * 
     * @param name
     * @param value
     */
    protected void setRequestAttribute(String name, Object value) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        requestAttributes.setAttribute(name, value, RequestAttributes.SCOPE_REQUEST);
    }

    /**
     * 设置请求上下文参数
     * 
     * @param name request中传递的参数名称
     * @param value
     */
    @SuppressWarnings("unchecked")
    public <T> T getRequestAttribute(String name) {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
        } catch (IllegalStateException e) {
            return null;
        }
    }

    /**
     * 通过注解方式验证对象的属性数据正确性
     * 
     * @param obj
     * @return 返回验证结果
     */
    protected BindingResult validate(Object obj) {
        BindingResult result = new BeanPropertyBindingResult(obj, obj.getClass().getName());
        validator.validate(obj, result);
        return result;
    }

    /**
     * 将validator 检验出的错误信息封装成统一的消息错误对象
     * 
     * @param result
     * @return
     */
    protected List<ResponseError> getErrors(BindingResult result) {
        List<ResponseError> errors = new ArrayList<ResponseError>();
        if (!result.hasErrors()) {
            return errors;
        }
        List<ObjectError> objectErrors = result.getAllErrors();
        for (ObjectError objectError : objectErrors) {
            ResponseError error = new ResponseError();
            error.add(objectError.getCode(), objectError.getDefaultMessage());
            errors.add(error);
        }

        return errors;
    }

    public void setNotTest(boolean isNotTest) {
        this.isNotTest = isNotTest;
    }

    public boolean isNotTest() {
        return isNotTest;
    }

    protected HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 将正确信息封装成国际化后的对象
     * 
     * @param code 信息编码
     * @param args 信息参数
     * @return response message 对象
     */
    protected ResponseMessage successResponse(String code, Object... args) {
        ResponseMessage message = new ResponseMessage(getMessageSourceService());
        message.setCode(code);
        message.setArgs(args);
        return message;
    }

    /**
     * 只是向前端传递参数，不用国际化
     * 
     * @param key
     * @param value
     * @return
     */
    protected ResponseMessage successDataResponse(String key, Object value) {
        ResponseMessage message = new ResponseMessage();
        message.putData(key, value);
        return message;
    }

    /**
     * 将错误信息封装成国际化后的对象
     * 
     * @param code 信息编码
     * @param args 信息参数
     * @return response message 对象
     */
    protected ResponseMessage errorResponse(String code, Object... args) {
        ResponseMessage message = new ResponseMessage(getMessageSourceService());
        message.addError(code, args);
        return message;
    }

    /**
     * 根据异常代码获取错误消息
     * 
     * @param errorCode 错误码
     * @param args 错误参数
     * @return 国际化后的错误信息
     */
    protected String getI18nMsg(String errorCode, Object... args) {
        return getMessageSourceService().getI18nMsg(getHttpServletRequest(), errorCode, args);
    }

    @Override
    public IMessageSourceService getMessageSourceService() {
        return messageSourceService;
    }

}

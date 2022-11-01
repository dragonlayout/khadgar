package com.dragonlayout.khadgar.interceptor;

import com.dragonlayout.khadgar.common.Response;
import com.dragonlayout.khadgar.common.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * {@link HttpRequestMethodNotSupportedException}
 * {@link BindException}
 * {@link MethodArgumentNotValidException}
 * {@link ConstraintViolationException}
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 请求方法不支持
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response<?> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error("请求地址'{}',不支持'{}'请求", request.getRequestURI(), e.getMethod());
        return Response.fail(ResponseCode.Client.REQUEST_METHOD_INVALID, e.getMethod());
    }

    /**
     * 请求参数校验异常 {@link javax.validation.Valid} {@link org.springframework.web.bind.annotation.RequestParam}
     * TODO: 报错信息如何封装返回
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Response<?> handleBindException(BindException e) {
        log.error(e.getMessage(), e);
//        List<Map<String, String>> list = new ArrayList<>();
//        for (ObjectError objectError: e.getAllErrors()) {
//            Map<String, String> map = new HashMap<>();
//            if (objectError instanceof FieldError) {
//                FieldError fieldError = (FieldError) objectError;
//                map.put("field", fieldError.getField());
//                map.put("message", fieldError.getDefaultMessage());
//            } else {
//                map.put("field", objectError.getObjectName());
//                map.put("message", objectError.getDefaultMessage());
//            }
//            list.add(map);
//        }
//        StringBuilder message = new StringBuilder();
//        List<FieldError> fieldErrorList = e.getFieldErrors();
//        buildMessage(fieldErrorList, message);
        return Response.fail();
//        return Response.fail(ResponseCode.Client.REQUEST_PARAMETERS_INVALID, message);
    }

    /**
     * 请求体校验异常 {@link javax.validation.Valid} {@link org.springframework.web.bind.annotation.RequestBody}
     * TODO: 报错信息如何封装返回
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        // error message 1
        List<String> errors = new ArrayList<String>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        System.out.println("errors:" + errors);
        // error message 2
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();
        buildMessage(fieldErrorList, message);
        return Response.fail(ResponseCode.Client.REQUEST_PARAMETERS_INVALID, message);
    }

    /**
     * Spring 提供的参数校验支持 {@link org.springframework.validation.annotation.Validated}
     * TODO: 报错信息如何封装返回
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Response<?> handleConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
//        e.getConstraintViolations().stream().
        Map<Path, String> errorMap = e.getConstraintViolations().stream()
                .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
        System.out.println(errorMap);

        HashMap<String, String> error = new HashMap<>();

        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            Iterator<Path.Node> iterator = constraintViolation.getPropertyPath().iterator();
            while (iterator.hasNext()) {
                Path.Node last = iterator.next();
                if (!iterator.hasNext()) {
                    error.put(last.getName(), constraintViolation.getMessage());
                    break;
                }
            }
        }
        System.out.println(error);
        return Response.fail(ResponseCode.Client.REQUEST_PARAMETERS_INVALID, e.getMessage());
    }

    /**
     * Handle all other exceptions.
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response<?> handleAll(Exception e) {
        return Response.fail();
    }
    private void buildMessage(List<FieldError> fieldErrorList, StringBuilder message) {
        if (!CollectionUtils.isEmpty(fieldErrorList)) {
            message.append(fieldErrorList.stream()
                    .filter(fieldError -> fieldError != null && fieldError.getDefaultMessage() != null)
                    .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage() + ";")
                    .collect(Collectors.joining()));
        }
    }
}

package com.dragonlayout.khadgar.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * api 响应封装对象
 * @param <T>
 */
@Data
@AllArgsConstructor
public class Response<T> {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应码提示信息
     */
    private String message;

    /**
     * 数据对象
     */
    private T data;

    public static <T> Response<T> ok() {
        return build(ResponseCode.SUCCESS, null);
    }

    public static <T> Response<T> ok(T data) {
        return build(ResponseCode.SUCCESS, data);
    }

    public static <T> Response<T> fail() {
        return build(ResponseCode.FAIL, null);
    }

    public static <T> Response<T> fail(T data) {
        return build(ResponseCode.FAIL, data);
    }

    public static <T> Response<T> fail(ResponseCodeInterface responseCodeInterface) {
        return build(responseCodeInterface, null);
    }

    public static <T> Response<T> fail(ResponseCodeInterface responseCodeInterface, Object... args) {
        return build(responseCodeInterface, null, args);
    }

    /**
     * TODO:
     * @param responseCodeInterface
     * @param data
     * @param args
     * @return
     * @param <T>
     */
    public static <T> Response<T> build(ResponseCodeInterface responseCodeInterface, T data, Object... args) {
        return new Response<>(responseCodeInterface.code(), String.format(responseCodeInterface.message(), args), data);
    }

    /**
     *
     * @param responseCodeInterface
     * @param data
     * @return
     * @param <T>
     */
    public static <T> Response<T> build(ResponseCodeInterface responseCodeInterface, T data) {
        return new Response<>(responseCodeInterface.code(), responseCodeInterface.message(), data);
    }

    /**
     *
     * @param code 响应码
     * @param message 响应码对应信息
     * @return
     * @param <T>
     */
    public static <T> Response<T> build(Integer code, String message) {
        return new Response<>(code, message, null);
    }
}

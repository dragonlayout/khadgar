package com.dragonlayout.khadgar.common;


/**
 * TODO: 错误码设计原则
 * 0~9999 为保留响应码或常用响应码
 * 10000~19999 为内部响应码
 * 20000~29999 为客户端响应码, 客户端异常调用等
 * 30000~39999 为第三方响应码, 第三方异常
 * 40000~49999 为业务响应码, 返回提示给用户
 *
 * 2XXX 业务执行成功
 * 4XXX 由于用户信息导致的错误
 * 5XXX 由于系统原因导致的错误
 */
public enum ResponseCode implements ResponseCodeInterface {
    SUCCESS(0, "成功"),
    FAIL(5000, "失败"),
    UNKNOWN_ERROR(5999,"未知错误"),
    ;


    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }

    /**
     * 客户端响应码汇总
     * 20000~29999
     */
    public enum Client implements ResponseCodeInterface {
        FORBIDDEN_TO_CALL(Module.COMMON, 1,"禁止调用"),
        REQUEST_TO_OFTEN(Module.COMMON, 2, "调用太过频繁"),
        REQUEST_PARAMETERS_INVALID(Module.COMMON, 3, "请求参数异常，'%s'"),
        REQUEST_METHOD_INVALID(Module.COMMON, 4, "请求方法: '%s' 不支持"),
        NO_AUTHORIZATION(Module.PERMISSION, 1, "请求接口：'%s' 失败，用户未授权"),
        ;

        private final int code;
        private final String message;

        private static final int BASE_CODE = 20000;

        Client(Module module, int code, String message) {
            this.code = BASE_CODE + module.getCode() + code;
            this.message = message;
        }

        @Override
        public int code() {
            return this.code;
        }

        @Override
        public String message() {
            return this.message;
        }
    }

}

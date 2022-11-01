package com.dragonlayout.khadgar.common;

/**
 * 系统内模块
 */
public enum Module {
    /**
     * 通用模块
     */
    COMMON(0),

    /**
     * 权限模块
     */
    PERMISSION(1),
    LOGIN(2),
    DB(3),
    UPLOAD(4),
    USER(5),
    CONFIG(6),
    POST(7),
    DEPT(8),
    MENU(9),
    ROLE(10),
    ;

    private final int code;

    Module(int code) {
        this.code = code * 100;
    }

    public int getCode() {
        return code;
    }
}

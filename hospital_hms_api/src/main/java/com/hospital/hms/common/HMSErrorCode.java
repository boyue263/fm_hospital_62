package com.hospital.hms.common;

public  enum HMSErrorCode {
    DELETE_DOCTOR_FAILED(1001, "删除医生信息失败"),
    INVALID_DOCTOR_ID(1002, "无效的医生ID"),

    // 可以继续添加其他错误类型
    ;

    private final int code;
    private final String message;

    // 构造函数
    HMSErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
package com.hospital.wx.exception;
import lombok.Data;

@Data
public class GlobalException extends RuntimeException {
    private String msg;

    private int code = 500;

    public GlobalException(Exception e) {
        super(e);
        this.msg = e.getMessage();
        this.code = 500;
    }

    public GlobalException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public GlobalException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public GlobalException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public GlobalException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

}
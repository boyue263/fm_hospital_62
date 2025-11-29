package com.hospital.hms.common;

public enum OperationMessage {
    PLAN_EXISTS("已经存在出诊计划，不能重复添加"),
    PLAN_SAVE_OK("添加完成"),
    DAILY_REGISTRATION_LIMIT_REACHED("已经达到当天挂号上限"),
    ROOM_ALREADY_REGISTERED("已经挂过该诊室的号"),
    FACE_MODEL_NOT_FOUND("不存在面部模型"),
    NO_FACE_VERIFICATION_RECORD("当日没有人脸验证记录"),
    REGISTRATION_ELIGIBLE("满足挂号条件"),
    FACE_VERIFICATION_FAILED("人脸验证失败")
    ;

    private final String message;

    OperationMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

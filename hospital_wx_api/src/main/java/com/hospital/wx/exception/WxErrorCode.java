package com.hospital.wx.exception;

public enum WxErrorCode {

    /**
     * 错误类型一：
     * */
    LOGIN_OR_REGISTER_FAILED(2001, "登录或注册失败"),
    SAVE_PATIENT_INFO_FAILED(2002, "就诊卡信息保存失败"),
    CARD_INFO_QUERY_FAILED(2003, "就诊卡信息查询失败"),
    UPDATE_PATIENT_INFO_FAILED(2004, "就诊卡信息更新失败"),
    USER_EXISTS_QUERY_FAILED(2005, "患者信息查询失败"),
    PLAN_INFO_QUERY_FAILED(2006, "查询医生排班信息时出错"),
    DOCTOR_SCHEDULE_QUERY_FAILED(2007, "查询指定日期的出诊医生时出错"),
    FACE_MODEL_CREATION_FAILED(2008, "录入患者人脸信息时出错"),
    FACE_MODEL_VERIFICATION_FAILED(2009, "验证患者人脸信息时出错"),

    DOCTOR_INFO_QUERY_FAILED(3001, "查询医生信息时出错"),
    DOCTOR_REGISTRATION_SLOT_QUERY_FAILED(3002, "查询医生挂号时段信息出错"),
    REGISTER_MEDICAL_APPOINTMENT_FAILED(3003,"预约挂号失败"),
    REGISTRATION_QUERY_FAILED(4001,"挂号信息查询失败"),
    REGISTRATION_DETAIL_QUERY_FAILED(4002,"查询挂号详情时出错"),

    /**
     * 错误类型二：
     * */
    PLAN_EXISTS("已经存在出诊计划，不能重复添加"),
    PLAN_SAVE_OK("添加完成"),
    DAILY_REGISTRATION_LIMIT_REACHED("已经达到当天挂号上限"),
    ROOM_ALREADY_REGISTERED("已经挂过该诊室的号"),
    FACE_MODEL_NOT_FOUND("不存在面部模型"),
    NO_FACE_VERIFICATION_RECORD("当日没有人脸验证记录"),
    REGISTRATION_ELIGIBLE("满足挂号条件"),
    CHECK_REGISTER_CONDITION_FAILED("检查挂号条件失败"),
    FACE_VERIFICATION_FAILED("人脸验证失败"),

    /**
     * 视频问诊
     * */
    DOCTOR_ONLINE_FAILED("医生上线失败"),
    GENERATE_USER_SIG_FAILED("生成用户签名失败");



    private final Integer code;
    private final String message;
    // 构造函数
    WxErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    WxErrorCode(String message) {
        this.code = null;  // 对于没有 code 的错误码，code 设置为 null
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

package com.hospital.wx.service;
import cn.felord.payment.wechat.v3.model.ResponseSignVerifyParams;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;

public interface PaymentService {


    ObjectNode createPaymentOrder(String outTradeNo, String openId, int total, String desc, String notifyUrl, String timeExpire);

    public String searchPaymentResult(String outTradeNo);

    ObjectNode mockCreatePaymentOrder(String outTradeNo, String openId, int total, String desc, String notifyUrl, String timeExpire);

    Map handleWechatPaymentCallback(ResponseSignVerifyParams params);

}

package com.hospital.wx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;

public class Test01 {

    public static void main(String[] args) {
        ObjectNode objectNode = mockCreatePaymentOrder("1", "1", 1, "1", "a", "a");
        String prepayId = objectNode.get("prepay_id").asText();
        String timeStamp = objectNode.get("timeStamp").asText();
        String nonceStr = objectNode.get("nonceStr").asText();
        String aPackage = objectNode.get("package").asText();
        String signType = objectNode.get("signType").asText();
        String paySign = objectNode.get("paySign").asText();

    }

    public static ObjectNode mockCreatePaymentOrder(String outTradeNo, String openId, int total, String desc, String notifyUrl, String timeExpire) {
        // 创建 ObjectMapper 对象，用于处理 JSON 数据
        ObjectMapper objectMapper = new ObjectMapper();

        // 创建一个 JSON 对象节点
        ObjectNode response = objectMapper.createObjectNode();

        // 设置预支付订单 ID（模拟数据）
        response.put("prepay_id", "wx201410272009395522657a690389285100");

        // 设置支付方式，微信支付需要这个固定值
        response.put("package", "Sign=WXPay");

        // 生成随机字符串（模拟数据）
        response.put("nonceStr", "5K8264ILTKCH16CQ2502SI8ZNMTM67VS");

        // 生成当前时间戳（秒），用于支付请求的时间参数
        response.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));

        // 设置签名类型，微信支付通常使用 MD5 或 HMAC-SHA256
        response.put("signType", "MD5");

        // 设置支付签名（模拟数据），在真实环境下应根据微信支付 API 规则计算签名
        response.put("paySign", "MOCK_SIGNATURE_123456");

        // 返回 JSON 响应对象
        return response;
    }


}

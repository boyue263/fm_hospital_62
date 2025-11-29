package com.hospital.wx.service.impl;
import cn.felord.payment.PayException;
import cn.felord.payment.wechat.v3.WechatApiProvider;
import cn.felord.payment.wechat.v3.WechatResponseEntity;
import cn.felord.payment.wechat.v3.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hospital.wx.service.PatientRegistrationService;
import com.hospital.wx.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Service
public class PaymentServiceImpl implements PaymentService {

//    @Resource
//    private WechatApiProvider wechatApiProvider;

    private final String WECHAT_API_KEY = "hospital-wx-api";
    @Autowired
    private PatientRegistrationService patientRegistrationService;


    /**
     * 生产环境支付代码
     * */
    @Override
    public ObjectNode createPaymentOrder(String outTradeNo, String openId, int total, String desc, String notifyUrl, String timeExpire) {
        if (outTradeNo == null || openId == null || desc == null || notifyUrl == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        PayParams params = new PayParams();
        // 设置付款人
        Payer payer = new Payer();
        payer.setOpenid(openId);
        params.setPayer(payer);

        // 设置付款金额（单位：分）
        Amount amount = new Amount();
        //amount.setTotal(total);
        amount.setTotal(1);
        params.setAmount(amount);

        params.setPayer(payer);
        params.setAmount(amount);
        params.setOutTradeNo(outTradeNo);
        params.setDescription(desc);
        params.setNotifyUrl(notifyUrl);
        /*
        等价于：
        if (timeExpire != null) {
            params.setTimeExpire(timeExpire);
        }
        * */
        Optional.ofNullable(timeExpire).ifPresent(params::setTimeExpire);

        // 发送支付请求
//        WechatResponseEntity<ObjectNode> response = wechatApiProvider.directPayApi(WECHAT_API_KEY).jsPay(params);
        WechatResponseEntity<ObjectNode> response = null;
        if (!response.is2xxSuccessful()) {
            throw new PayException("创建微信支付订单失败，错误信息: " + response.getBody());
        }
        /*
          {
              "prepay_id": "wx201410272009395522657a690389285100",
              "appid": "wx2421b1c4370ec43b",
              "partnerid": "1900000109",
              "package": "Sign=WXPay",
              "noncestr": "5K8264ILTKCH16CQ2502SI8ZNMTM67VS",
              "timestamp": "1412000000",
              "sign": "C380BEC2BFD727A4B6845133519F3AD6"
          }
            prepay_id	预支付订单 ID，前端调用支付时必须用它。
            appid	商户的微信应用 ID，必须与支付请求一致。
            partnerid	商户号（微信支付分配），用于标识商家。
            package	订单扩展信息（JSAPI 需要 "prepay_id=xxx"，App 需要 "Sign=WXPay"）。
            noncestr	随机字符串，保证安全性，每次请求都不同。
            timestamp	时间戳，避免请求重放攻击，前端调用时必须使用相同值。
            sign	服务器计算的签名，保证支付参数未被篡改。
        *
        * */
        return response.getBody();
    }

    /**
     * Mock结果集
     * 在微信支付流程中，prepay_id（预支付交易会话标识）是微信支付服务器生成的一个唯一标识，用于后续支付请求。它的作用如下：
     * 标识一次预支付交易：
     * 这是在商户服务器向微信支付后台请求预支付订单（/v3/pay/transactions/app 或 /v3/pay/transactions/jsapi 等接口）后，微信支付返回的一个 prepay_id，表示该订单已经成功创建。
     *      用于支付请求：
     *      在客户端（App、小程序、H5等）发起支付时，需要带上 prepay_id，微信支付 SDK 才能拉起支付界面，让用户完成付款
     *
     * */

    public ObjectNode mockCreatePaymentOrder(String outTradeNo, String openId, int total, String desc, String notifyUrl, String timeExpire) {
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

    @Override
    public String searchPaymentResult(String outTradeNo) {
//        TransactionQueryParams params = new TransactionQueryParams();
//        params.setTransactionIdOrOutTradeNo(outTradeNo);
//        WechatResponseEntity<ObjectNode> entity = wechatApiProvider.directPayApi(WECHAT_API_KEY).jsPay(params)
//            .queryTransactionByOutTradeNo(params);
//        ObjectNode body = entity.getBody();
//        JsonNode tradeState = body.get("trade_state");
//        if (tradeState.asText().equals("SUCCESS")) {
//            //如果患者已支付挂号费，我们取出支付单transaction_id，这个需要保存到挂号表
//            String transactionId = body.get("transaction_id").asText();
//            return transactionId;
//        }
        return UUID.randomUUID().toString();
    }


    /**
     * 生成环境代码：
     * 处理微信付款通知的回调函数
     */
    @Override
    public Map handleWechatPaymentCallback(ResponseSignVerifyParams params) {
        //验证通知消息的真伪
        /**
         * transactionCallback(params, this::handlePaymentResult)  等价于 =>  transactionCallback(params, data -> handlePaymentResult(data));
            下面的传统写法：
            transactionCallback(params, new Consumer<TransactionConsumeData>() {
                @Override
                public void accept(TransactionConsumeData data) {
                    handlePaymentResult(data);
                }
              });
         this::handlePaymentResult 其实就是把transactionCallback 处理后的结果作为参数传递给 handlePaymentResult 进行处理。
         */
        return null;
//        return wechatApiProvider.callback(WECHAT_API_KEY).transactionCallback(params, this::handlePaymentResult);
    }

    private void handlePaymentResult(TransactionConsumeData data) {
        String outTradeNo = data.getOutTradeNo();
        String transactionId = data.getTransactionId();

        // 更新挂号记录的付款状态和付款单ID
        Map<String, Object> paymentUpdateParams = new HashMap<>();
        paymentUpdateParams.put("outTradeNo", outTradeNo);
        paymentUpdateParams.put("transactionId", transactionId);
        paymentUpdateParams.put("paymentStatus", 2);
        patientRegistrationService.updatePaymentStatus(paymentUpdateParams);
    }

}

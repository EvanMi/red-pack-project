package com.yumi.red.pack.pay.api;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.yumi.red.pack.pay.acl.AlipayAcl;
import com.yumi.red.pack.pay.acl.PropertiesConfig;
import com.yumi.red.pack.pay.dto.AlipayBean;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
@RequestMapping("/test")
@Slf4j
public class PayService {

    @Resource
    private AlipayAcl acl;

    @GetMapping("pay/{id}")
    public RedirectView test(@PathVariable("id") String id) throws AlipayApiException {
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(id);
        alipayBean.setSubject("测试订单");
        alipayBean.setTotal_amount("12");
        String ids= "courseId" +","+ "yumi" + "," + id;
        alipayBean.setBody(ids);
        return new RedirectView(acl.pay(alipayBean));
    }


    @PostMapping("/tradeNotify")
    public String tradeNotify(@RequestParam Map<String, String> params) {
        log.info("支付通知,正在执行,通知参数:{}", JSON.toJSONString(params));
        return acl.tradeNotify(params);
    }

    @GetMapping("/tradeCallback")
    public RedirectView tradeCallback(@RequestParam Map<String, String> params) throws AlipayApiException {
        log.info("支付回调,正在执行,通知参数:{}", JSON.toJSONString(params));
        boolean signVerified = AlipaySignature.rsaCheckV1(params,
                PropertiesConfig.PUBLIC_KEY,
                AlipayConstants.CHARSET_UTF8,
                AlipayConstants.SIGN_TYPE_RSA2);
        log.info("signVerified: {}", signVerified);
        return new RedirectView("https://www.baidu.com");
    }
}

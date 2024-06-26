package org.moroboshidan.serviceverificationcode.controller;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.dto.serviceverificationcode.request.VerificationCodeRequest;
import org.moroboshidan.serviceverificationcode.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verification-code")
@Slf4j
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;

    /*
     * @description: 生成验证码，将结果返回给调用方，调用sms服务给用户发送短信，并将验证码存储在redis中
     * @param identity
     * @param phoneNumber
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/6/26 18:59
     */
    @GetMapping("/generate/{identity}/{phoneNumber}")
    public ResponseResult generateVerificationCode(@PathVariable int identity, @PathVariable String phoneNumber) {
        log.info("/verification-code/generate/{identity}/{phoneNumber} | 身份类型: " + identity + ", 手机号: " + phoneNumber);
        return verificationCodeService.generate(identity, phoneNumber);
    }

    /*
     * @description: 校验验证码，查看接收到的验证码和redis中存储的验证码是否一致
     * @param identity
     * @param phoneNumber
     * @param verificationCode
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/6/26 18:59
     */
    @PostMapping("/verify")
    public ResponseResult verifyVerificationCode(@RequestBody VerificationCodeRequest request) {
        log.info("/verification-code/verify | request: " + JSONObject.fromObject(request));
        int identity = request.getIdentity();
        String phoneNumber = request.getPhoneNumber();
        String code = request.getCode();
        return verificationCodeService.verify(identity, phoneNumber, code);
    }
}

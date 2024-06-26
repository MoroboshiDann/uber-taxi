package org.moroboshidan.serviceverificationcode.service.impl;

import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.constant.IdentityConstant;
import org.moroboshidan.internalcommon.constant.RedisKeyPrefixConstant;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.dto.serviceverificationcode.response.VerificationCodeResponse;
import org.moroboshidan.serviceverificationcode.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ResponseResult<VerificationCodeResponse> generate(int identity, String phoneNumber) {
        String code = String.valueOf((Math.random() * 9 + 1) * Math.pow(10, 5));
        String redisKeyPrefix = generateKeyPrefixByIdentity(identity);
        String redisKey = redisKeyPrefix + phoneNumber;
        // 将code存储至redis，存活时间为2min
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(redisKey);
        ops.set(code, 2, TimeUnit.MINUTES);
        return ResponseResult.success(new VerificationCodeResponse(code));
    }

    /*
     * @description: 根据身份类型，来生成redis key的前缀
     * @param identity
     * @return: java.lang.String
     * @author: MoroboshiDan
     * @time: 2024/6/26 19:19
     */
    private String generateKeyPrefixByIdentity(int identity) {
        String prefix = "";
        if (identity == IdentityConstant.PASSENGER) {
            prefix = RedisKeyPrefixConstant.PASSENGER_LOGIN_CODE_KEY_PREFIX;
        } else if (identity == IdentityConstant.DRIVER) {
            prefix = RedisKeyPrefixConstant.DRIVER_LOGIN_CODE_KEY_PREFIX;
        }
        return prefix;
    }

    /*
     * @description: 校验验证码
     * @param identity
     * @param phoneNumber
     * @param code
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/6/26 19:26
     */
    @Override
    public ResponseResult verify(int identity, String phoneNumber, String code) {
        // 三档验证

        // 生成redis key
        String redisKeyPrefix = generateKeyPrefixByIdentity(identity);
        String redisKey = redisKeyPrefix + phoneNumber;
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(redisKey);
        String verificationCode = ops.get();
        if (StringUtils.isNotBlank(code)
            && StringUtils.isNotBlank(verificationCode)
            && code.trim().equals(verificationCode.trim())) {
            return ResponseResult.success("");
        } else {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());

        }
    }
}

package org.moroboshidan.serviceverificationcode.service.impl;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.dto.serviceverificationcode.VerificationCodeResponse;
import org.moroboshidan.serviceverificationcode.service.VerificationCodeService;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Override
    public ResponseResult<VerificationCodeResponse> generate(int identity, String phoneNumber) {
        String code = (Math.random() * 9 + 1+ "").substring(2, 8);
        return ResponseResult.success(new VerificationCodeResponse(code));
    }
}

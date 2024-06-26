package org.moroboshidan.serviceverificationcode.service;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.dto.serviceverificationcode.response.VerificationCodeResponse;

public interface VerificationCodeService {
    public ResponseResult<VerificationCodeResponse> generate(int identity, String phoneNumber);

    ResponseResult verify(int identity, String phoneNumber, String code);

}

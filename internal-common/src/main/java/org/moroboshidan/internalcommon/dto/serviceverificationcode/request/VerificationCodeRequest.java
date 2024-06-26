package org.moroboshidan.internalcommon.dto.serviceverificationcode.request;

import lombok.Data;

@Data
public class VerificationCodeRequest {
    private int identity;
    private String phoneNumber;
    private String code;
}

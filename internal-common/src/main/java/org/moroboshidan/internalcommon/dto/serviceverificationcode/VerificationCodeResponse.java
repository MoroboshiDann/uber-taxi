package org.moroboshidan.internalcommon.dto.serviceverificationcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationCodeResponse {
    private String code;
}

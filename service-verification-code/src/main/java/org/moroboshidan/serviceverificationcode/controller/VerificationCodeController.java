package org.moroboshidan.serviceverificationcode.controller;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verification-code")
public class VerificationCodeController {
    @GetMapping("/generate/{identity}/{phoneNumber}")
    public ResponseResult generateVerificationCode(@PathVariable int identity, @PathVariable String phoneNumber) {
        return ResponseResult.success(null);
    }
}

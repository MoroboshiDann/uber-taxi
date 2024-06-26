package org.moroboshidan.internalcommon.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;

import java.io.Serializable;

@Data
@Accessors(chain=true)
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T data;

    /*
     * @description: 返回成功数据(status: 1)
     * @param data
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/6/25 19:31
     */
    public static <T> ResponseResult success(T data) {
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }

    /**
     * 返回错误数据（status：500）
     *
     * @param data 错误内容
     * @param <T>  数据类型
     * @return ResponseResult实例
     */
    public static <T> ResponseResult fail(T data) {
        return new ResponseResult().setCode(CommonStatusEnum.INTERNAL_SERVER_EXCEPTION.getCode()).setMessage(CommonStatusEnum.INTERNAL_SERVER_EXCEPTION.getValue()).setData(data);
    }

    /**
     * 自定义返回错误数据
     *
     * @param code    错误代码
     * @param message 错误消息
     * @return ResponseResult实例
     */
    public static ResponseResult fail(int code, String message) {
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * @param code    错误代码
     * @param message 错误消息
     * @param data    错误内容
     * @return ResponseResult实例
     */
    public static ResponseResult fail(int code, String message, String data) {
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }
}

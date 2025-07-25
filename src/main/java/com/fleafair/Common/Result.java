package com.fleafair.Common;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回结果
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {
    private Integer code;   // 状态码,200为成功，400和其他状态码为失败
    private String msg;     // 提示信息
    private T data;         // 数据体

    // 无数据成功返回
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = 200;
        result.msg = "操作成功";
        return result;
    }

    // 有数据成功返回
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.msg = "操作成功";
        result.data = data;
        return result;
    }

    // 带消息的成功返回
    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.msg = msg;
        result.data = data;
        return result;
    }

    // 失败返回
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 400;
        return result;
    }

    // 带状态码的失败返回
    public static <T> Result<T> error(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        return result;
    }

    // 带状态码和数据的失败返回
    public static <T> Result<T> error(Integer code, String msg, T data) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        result.data = data;
        return result;
    }
}
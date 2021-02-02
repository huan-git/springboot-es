package com.huan.es.common.api;

/**
 * 封装API的错误码
 * Created by ahuan on 2019/4/19.
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
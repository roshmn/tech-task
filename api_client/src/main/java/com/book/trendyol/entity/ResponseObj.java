package com.book.trendyol.entity;

import com.book.trendyol.entity.dao.ApiError;

import java.util.List;

public class ResponseObj<T> {
    private int code;
    private T bodyAsSingleObj;
    private List<T> bodyAsListObj;
    private ApiError error;

    public ResponseObj() {
    }

    public ResponseObj(int statusCode, ApiError error) {
        this.code = statusCode;
        this.error = error;
    }

    public ResponseObj(int statusCode, T obj) {
        this.code = statusCode;
        this.bodyAsSingleObj = obj;
    }

    public ResponseObj(int statusCode, List<T> listObj) {
        this.code = statusCode;
        this.bodyAsListObj = listObj;
    }

    public int getCode() {
        return code;
    }

    public T getBodySingleObj() {
        return bodyAsSingleObj;
    }

    public List<T> getBodyAsListObj() {
        return bodyAsListObj;
    }

    public ApiError getApiError() {
        return error;
    }
}

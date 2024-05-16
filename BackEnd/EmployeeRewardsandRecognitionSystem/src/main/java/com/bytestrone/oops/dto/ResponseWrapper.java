package com.bytestrone.oops.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResponseWrapper<T> {
    private T data;
    private ErrorResponse error;

    public ResponseWrapper(T data, ErrorResponse error) {
        this.data = data;
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }
}

package br.com.bagnascojhoel.demo_sor.common;

public class Error {
    private String message;
    private ErrorCode code;

    public Error(
            final String message,
            final ErrorCode errorCode) {
        this.message = message;
        this.code = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode getCode() {
        return code;
    }
}

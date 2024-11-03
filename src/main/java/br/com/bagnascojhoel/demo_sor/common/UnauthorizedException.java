package br.com.bagnascojhoel.demo_sor.common;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(
            String message) {
        super(message);
    }

}

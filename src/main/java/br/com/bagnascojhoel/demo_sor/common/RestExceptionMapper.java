package br.com.bagnascojhoel.demo_sor.common;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import jakarta.ws.rs.core.Response;

public class RestExceptionMapper {

    private static final Logger logger = Logger.getLogger(RestExceptionMapper.class);

    @ServerExceptionMapper
    public RestResponse<Error> mapException(RuntimeException exception) {
        logger.error("internal server error", exception);
        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR,
                new Error("internal server error", ErrorCode.UNEXPECTED));
    }

    @ServerExceptionMapper
    public RestResponse<Error> mapException(
            org.hibernate.exception.ConstraintViolationException constraintViolationException) {
        logger.warn("hibernate triggered a constraint violation", constraintViolationException);
        return RestResponse.status(Response.Status.BAD_REQUEST,
                new Error(constraintViolationException.getLocalizedMessage(), ErrorCode.FAILED_VALIDATION));
    }

    @ServerExceptionMapper
    public RestResponse<Error> mapException(
            UnauthorizedException unauthorizedException) {
        logger.warn("not authorized request");
        return RestResponse.status(Response.Status.UNAUTHORIZED,
                new Error(unauthorizedException.getMessage(), ErrorCode.UNAUTHORIZED));
    }

}

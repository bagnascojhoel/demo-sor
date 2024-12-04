package br.com.bagnascojhoel.demo_sor.common;

import java.io.IOException;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_PREFIX = "Bearer ";
    private static final int INITIAL_TOKEN_INDEX = AUTHORIZATION_PREFIX.length() - 1;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    @ConfigProperty(name = "demo-sor.auth.token")
    private jakarta.inject.Provider<String> authorizedToken;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString("authorization");

        if (authorizationHeader == null) {
            throw new UnauthorizedException("you are not authenticated");
        }

        if (!authorizationHeader.startsWith(AUTHORIZATION_PREFIX)) {
            throw new UnauthorizedException("authorization value is wrongly formatted");
        }

        String authorizationToken = authorizationHeader.substring(INITIAL_TOKEN_INDEX);
        authorizationToken = authorizationToken.trim();

        if (!authorizationToken.equals(authorizedToken.get())) {
            throw new UnauthorizedException("authentication failed");
        }

        log.info("user authenticated");
    }

}

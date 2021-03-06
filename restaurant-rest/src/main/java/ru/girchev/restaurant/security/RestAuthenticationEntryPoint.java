package ru.girchev.restaurant.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 23.11.15.
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest arg0, HttpServletResponse arg1,
                         AuthenticationException arg2) throws IOException, ServletException {
        arg1.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

    }
}

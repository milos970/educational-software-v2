package com.milos.numeric.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler  implements AuthenticationFailureHandler
{


    private final SimpleUrlAuthenticationFailureHandler wrongCredentials =
            new SimpleUrlAuthenticationFailureHandler("/failure");


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException
    {
        System.out.println(5654656);
        this.wrongCredentials.onAuthenticationFailure(request, response, exception);
    }
}

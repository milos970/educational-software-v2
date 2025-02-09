package com.milos.numeric.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager
{
    private final List<AuthenticationProvider> providers;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        System.out.println("Manager is being called");
        Authentication auth = null;

        try {
            auth = providers.get(0).authenticate(authentication);
        } catch (AuthenticationException e) {
            auth.setAuthenticated(false);
            return auth;
        }

        try {
            auth =  providers.get(1).authenticate(auth);
        } catch (AuthenticationException e) {
            auth.setAuthenticated(false);
            return auth;
        }

        return auth;

    }
}

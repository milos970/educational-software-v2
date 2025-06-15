package com.milos.numeric.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager
{
    private final List<AuthenticationProvider> providers;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        Authentication auth = null;

        try {
            auth = providers.get(0).authenticate(authentication);
        } catch (AuthenticationException e) {
            auth.setAuthenticated(false);
            return auth;
        }

        try {
            providers.get(1).authenticate(auth);
        } catch (AuthenticationException e) {
            auth.setAuthenticated(false);
            return auth;
        }



        SecurityContextHolder.getContext().setAuthentication(auth);

        return auth;

    }
}

package com.milos.numeric.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CustomAuthenticationManager implements AuthenticationManager
{
    private final List<AuthenticationProvider> authenticationProviders;

    public CustomAuthenticationManager(List<AuthenticationProvider> authenticationProviders) {
        this.authenticationProviders = authenticationProviders;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        Authentication result = null;
        for (AuthenticationProvider provider : this.authenticationProviders)
        {
            try {
                result = provider.authenticate(authentication);
            } catch (Exception e)
            {
                System.out.println("FAILED!!!!!");
                return null;
            }
        }

        return result;
    }
}

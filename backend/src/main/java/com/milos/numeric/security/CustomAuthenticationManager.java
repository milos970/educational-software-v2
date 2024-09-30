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

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication result = null;

        for (AuthenticationProvider provider : authenticationProviders) {
            try {
                result = provider.authenticate(authentication);
                if (result != null && result.isAuthenticated()) {
                    System.out.println("Autentifikovaný cez poskytovateľa: " + provider.getClass().getSimpleName());
                }
            } catch (BadCredentialsException e) {

                System.out.println("Zlyhaná autentifikácia cez poskytovateľa: " + provider.getClass().getSimpleName());
            }
        }

        if (result == null || !result.isAuthenticated()) {
            throw new BadCredentialsException("Autentifikácia zlyhala na všetkých poskytovateľoch");
        }

        return result;
    }
}

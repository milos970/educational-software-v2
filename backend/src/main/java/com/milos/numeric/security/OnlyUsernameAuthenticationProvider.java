package com.milos.numeric.security;

import com.milos.numeric.services.MyDatabaseUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class OnlyUsernameAuthenticationProvider implements AuthenticationProvider
{
    @Autowired
    private  MyDatabaseUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getName();

        try
        {
            UserDetails user = this.userDetailsService.loadUserByUsername(username);

            return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
        }
        catch (UsernameNotFoundException e)
        {
            throw new BadCredentialsException("Username not found");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}

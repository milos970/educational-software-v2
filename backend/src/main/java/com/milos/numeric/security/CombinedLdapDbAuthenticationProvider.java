package com.milos.numeric.security;

import com.milos.numeric.entities.PersonalInfo;
import com.milos.numeric.repositories.PersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import java.util.List;
import java.util.Optional;

public class CombinedLdapDbAuthenticationProvider implements AuthenticationProvider {

    private final LdapTemplate ldapTemplate;
    @Autowired
    private  PersonalInfoRepository userRepository; // Tvoja JPA repository

    public CombinedLdapDbAuthenticationProvider(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        String userDn = "cn=" + username + ",ou=users,ou=system";

        try {
            DirContext ctx = ldapTemplate.getContextSource().getContext(userDn, password);
            ctx.close();

            PersonalInfo personalInfo = this.userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found in database: " + username));

            List<String> roles = ldapTemplate.search(
                    "ou=groups,ou=system",
                    "(member=" + userDn + ")",
                    (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get()
            );

            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                    .toList();

            return new UsernamePasswordAuthenticationToken(username, password,authorities);

        } catch (NamingException e) {
            throw new BadCredentialsException("LDAP authentication failed", e);
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}


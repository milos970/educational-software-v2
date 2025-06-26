package com.milos.numeric.security;


import com.milos.numeric.services.MyDatabaseUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception
    {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(mvc.pattern("api/auth/teacher")).hasRole("TEACHERS")
                        .requestMatchers(mvc.pattern("api/auth/student")).hasRole("STUDENTS")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();

    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }


    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrl("ldap://localhost:10389");
        ldapContextSource.setUserDn("uid=admin,ou=system");
        ldapContextSource.setPassword("secret");
        return ldapContextSource;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CombinedLdapDbAuthenticationProvider(ldapTemplate());
    }




    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler()
    {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler()
    {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        return new MyDatabaseUserDetailsService();
    }




}

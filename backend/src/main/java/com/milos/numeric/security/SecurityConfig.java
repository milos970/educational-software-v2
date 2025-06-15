package com.milos.numeric.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilderA = new MvcRequestMatcher.Builder(introspector);
        MvcRequestMatcher.Builder mvcMatcherBuilderB = new MvcRequestMatcher.Builder(introspector);
        MvcRequestMatcher.Builder mvcMatcherBuilderF = new MvcRequestMatcher.Builder(introspector);
        MvcRequestMatcher.Builder mvcMatcherBuilderG = new MvcRequestMatcher.Builder(introspector);
        MvcRequestMatcher.Builder mvcMatcherBuilderH = new MvcRequestMatcher.Builder(introspector);
        MvcRequestMatcher.Builder mvcMatcherBuilderL = new MvcRequestMatcher.Builder(introspector);
        MvcRequestMatcher.Builder mvcMatcherBuilderM = new MvcRequestMatcher.Builder(introspector);
        MvcRequestMatcher.Builder mvcMatcherAdmin = new MvcRequestMatcher.Builder(introspector);
        MvcRequestMatcher.Builder mvcMatcherStudent = new MvcRequestMatcher.Builder(introspector);
        http.csrf().disable().authorizeHttpRequests((authorize) -> authorize.requestMatchers(mvcMatcherBuilderA.pattern("/css/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilderB.pattern("/js/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilderF.pattern("/scss/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilderG.pattern("/vendor/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilderH.pattern("/vendors/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilderL.pattern("/create-token/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilderM.pattern("/confirm-email")).permitAll()
                        .requestMatchers(toH2Console()).permitAll()
                .requestMatchers(mvcMatcherAdmin.pattern("/system")).access(new WebExpressionAuthorizationManager("isAuthenticated() and principal.enabled == true and hasAnyAuthority('TEACHER', 'EMPLOYEE')"))
                .requestMatchers(mvcMatcherStudent.pattern("/profile")).access(new WebExpressionAuthorizationManager("isAuthenticated() and hasAuthority('STUDENT')"))
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login").permitAll().defaultSuccessUrl("/methods", true));
        http.authenticationManager(new CustomAuthenticationManager(Arrays.asList(usernameAuthenticationProvider(), ldapAuthenticationProvider())));

        return http.build();
    }





    @Bean
    public AuthenticationProvider usernameAuthenticationProvider() {
        return new OnlyUsernameAuthenticationProvider();
    }

    @Bean
    public LdapAuthenticationProvider ldapAuthenticationProvider() {

        return new LdapAuthenticationProvider(authenticator());
    }

    @Bean
    public BindAuthenticator authenticator() {
        FilterBasedLdapUserSearch search = new FilterBasedLdapUserSearch("ou=people", "(uid={0})", contextSource());
        BindAuthenticator authenticator = new BindAuthenticator(contextSource());
        authenticator.setUserSearch(search);
        return authenticator;
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return new DefaultSpringSecurityContextSource("ldap://localhost:8389/dc=springframework,dc=org");
    }


}

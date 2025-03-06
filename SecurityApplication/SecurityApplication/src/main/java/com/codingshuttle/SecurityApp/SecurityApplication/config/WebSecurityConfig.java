package com.codingshuttle.SecurityApp.SecurityApplication.config;

import com.codingshuttle.SecurityApp.SecurityApplication.entities.enums.Permission;
import com.codingshuttle.SecurityApp.SecurityApplication.filters.JwtAuthFilter;
import com.codingshuttle.SecurityApp.SecurityApplication.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.codingshuttle.SecurityApp.SecurityApplication.entities.enums.Role.ADMIN;
import static com.codingshuttle.SecurityApp.SecurityApplication.entities.enums.Role.CREATOR;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true) // with this we can use @Secured
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    private static final String[] publicRoutes = {
            "/auth/**", "/home.html"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicRoutes).permitAll() // it is used to declare what things/requests are public (NOTE: ONLY /posts route is public and does not auth but /posts/{postId} is not public and it needs the login)
                        .requestMatchers(HttpMethod.GET,"/posts/**").permitAll() // It is role based login any user who want to access the endpoint /posts/** will requirs ADMIN ROLE to ACCESS it.
                        .requestMatchers(HttpMethod.POST,"/posts/**")
                            .hasAnyRole(ADMIN.name(), CREATOR.name())

                        .requestMatchers(HttpMethod.POST,"/posts/**")
                            .hasAuthority(Permission.POST_CREATE.name())
                        .requestMatchers(HttpMethod.PUT,"/posts/**")
                            .hasAuthority(Permission.POST_UPDATE.name())
                        .requestMatchers(HttpMethod.DELETE,"/posts/**")
                            .hasAuthority(Permission.POST_DELETE.name())


                        .anyRequest().authenticated()) // It will authenticate any request it gets apart from above defined
                .csrf(csrfConfig -> csrfConfig.disable()) // it is used to disable csrf token
                .sessionManagement(sessionConfig -> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // it is used for the stateless session management
                .oauth2Login(oauth2config -> oauth2config
                        .failureUrl("/login?error=true")
                        .successHandler(oAuth2SuccessHandler)
                );

        return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

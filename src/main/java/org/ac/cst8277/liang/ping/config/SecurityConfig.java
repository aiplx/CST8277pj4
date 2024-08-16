package org.ac.cst8277.liang.ping.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ac.cst8277.liang.ping.service.UserManagementService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.UUID;

@Configuration
public class SecurityConfig {

    private final UserManagementService userManagementService;

    public SecurityConfig(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(customAuthenticationSuccessHandler())
                );
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

                // Generate a UUID token
                String token = UUID.randomUUID().toString();

                // Store the token using the UserManagementService
                userManagementService.storeTokenForUser(oAuth2User, token);

                // Display a message after successful login
                response.setContentType("text/html");
                response.getWriter().write("<html><body>");
                response.getWriter().write("<h1>Login Successful</h1>");
                response.getWriter().write("<p>You are now logged in. Please find your user's token in the Token table.</p>");
                response.getWriter().write("<p>Token generated from Github: " + token + "</p>");
                response.getWriter().write("</body></html>");

            }
        };
    }
}

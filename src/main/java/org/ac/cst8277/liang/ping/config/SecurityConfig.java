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
                        .anyRequest().authenticated()  // Secure all requests
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(customAuthenticationSuccessHandler())  // Use custom success handler
                );
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                // Extract the authenticated OAuth2 user
                OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

                // Generate a UUID token
                String token = UUID.randomUUID().toString();

                // Store the token using the UserManagementService
                userManagementService.storeTokenForUser(oAuth2User, token);

                // Display token information in the response
                response.setContentType("text/html");
                response.getWriter().write("<html><body>");
                response.getWriter().write("<h1>Login Successful</h1>");
                response.getWriter().write("<p>Token: " + token + "</p>");
                response.getWriter().write("</body></html>");

                // Alternatively, can redirect the user after successful authentication
                // response.sendRedirect("/?token=" + token);
            }
        };
    }
}


//@Component
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .anyRequest().authenticated()
//                )
//                .oauth2Login(oauth2Login ->
//                        oauth2Login
//                                .userInfoEndpoint(userInfoEndpoint ->
//                                        userInfoEndpoint.userService(oAuth2UserService())
//                                )
//                                .successHandler(authenticationSuccessHandler())
//                );
//        return http.build();
//    }
//
//    @Bean
//    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
//        return new DefaultOAuth2UserService();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return (request, response, authentication) -> {
//            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//            String token = UUID.randomUUID().toString();  // Generate a UUID token
//
//            // Store the token in your database or do any necessary processing
//            // For example:
//            // userManagementService.storeTokenForUser(oAuth2User, token);
//
//            // Redirect or respond with the token
//            response.sendRedirect("/?token=" + token);
//        };
//    }
//}
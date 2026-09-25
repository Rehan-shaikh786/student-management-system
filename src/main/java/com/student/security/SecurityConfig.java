package com.student.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http

                // Disable CSRF because this is a REST API
                .csrf(csrf -> csrf.disable())

                // JWT authentication is stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // Authorization rules
                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // PUBLIC APIs
                        // =========================
                        .requestMatchers(
                                "/api/auth/**"
                        ).permitAll()

                        // Swagger
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // =========================
                        // ADMIN ONLY - POST
                        // =========================
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/students/**",
                                "/api/courses/**",
                                "/api/enrollments/**"
                        ).hasRole("ADMIN")

                        // =========================
                        // ADMIN ONLY - PUT
                        // =========================
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/students/**",
                                "/api/courses/**"
                        ).hasRole("ADMIN")

                        // =========================
                        // ADMIN ONLY - DELETE
                        // =========================
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/students/**",
                                "/api/courses/**",
                                "/api/enrollments/**"
                        ).hasRole("ADMIN")

                        // =========================
                        // USER + ADMIN - GET
                        // =========================
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/students/**",
                                "/api/courses/**",
                                "/api/enrollments/**"
                        ).authenticated()

                        // Everything else requires authentication
                        .anyRequest().authenticated()
                )

                // =========================
                // EXCEPTION HANDLING
                // =========================
                .exceptionHandling(exception -> exception

                        // 401 - No valid authentication
                        .authenticationEntryPoint(
                                (request, response, authException) -> {

                                    response.setStatus(
                                            HttpServletResponse.SC_UNAUTHORIZED
                                    );

                                    response.setContentType(
                                            "application/json"
                                    );

                                    response.getWriter().write(
                                            """
                                            {
                                              "status": 401,
                                              "error": "Unauthorized",
                                              "message": "Authentication is required. Please provide a valid JWT token."
                                            }
                                            """
                                    );
                                }
                        )

                        // 403 - Authenticated but insufficient permission
                        .accessDeniedHandler(
                                accessDeniedHandler()
                        )
                )

                // JWT filter
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {

        return (request, response, accessDeniedException) -> {

            response.setStatus(
                    HttpServletResponse.SC_FORBIDDEN
            );

            response.setContentType(
                    "application/json"
            );

            response.getWriter().write(
                    """
                    {
                      "status": 403,
                      "error": "Forbidden",
                      "message": "You do not have permission to access this resource."
                    }
                    """
            );
        };
    }
}
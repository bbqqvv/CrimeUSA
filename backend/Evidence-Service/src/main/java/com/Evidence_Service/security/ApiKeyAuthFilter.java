package com.Evidence_Service.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.util.AntPathMatcher;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    @Value("${internal.api-key}")
    private String internalApiKey;

    private static final List<String> EXCLUDE_URLS = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/api-docs",
            "/api-docs/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/favicon.ico"
    );


    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println("internalApiKey = " + internalApiKey);
        String path = request.getRequestURI();

        boolean isSwagger = EXCLUDE_URLS.stream().anyMatch(pattern -> pathMatcher.match(pattern, path));
        if (isSwagger) {
            filterChain.doFilter(request, response);
            return;
        }

        // check API key
        String apiKey = request.getHeader("X-API-KEY");
        if (apiKey == null || !apiKey.equals(internalApiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid API Key");
            return;
        }

        // Get permission form header
        String permissionsHeader = request.getHeader("X-PERMISSION"); // "VIEW_EVIDENCE,EDIT_EVIDENCE"
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (permissionsHeader != null && !permissionsHeader.isBlank()) {
            String[] perms = permissionsHeader.split(",");
            for (String perm : perms) {
                authorities.add(new SimpleGrantedAuthority(perm.trim()));
            }
        }

        // Put Authentication to SecurityContext
        String username = request.getHeader("X-USERNAME");
        Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
}

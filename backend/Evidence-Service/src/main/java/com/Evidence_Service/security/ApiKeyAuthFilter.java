package com.Evidence_Service.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
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
    private String EXPECTED_API_KEY;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // check API key
        String apiKey = request.getHeader("X-Api-Key");
        if (apiKey == null || !apiKey.equals(EXPECTED_API_KEY)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid API Key");
            return;
        }

        // Get permission form header
        String permissionsHeader = request.getHeader("X-Permissions"); // "VIEW_EVIDENCE,EDIT_EVIDENCE"
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (permissionsHeader != null && !permissionsHeader.isBlank()) {
            String[] perms = permissionsHeader.split(",");
            for (String perm : perms) {
                authorities.add(new SimpleGrantedAuthority(perm.trim()));
            }
        }

        // Put Authentication to SecurityContext
        String username = request.getHeader("X-Username");
        Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
}

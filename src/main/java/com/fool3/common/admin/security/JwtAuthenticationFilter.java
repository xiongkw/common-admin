package com.fool3.common.admin.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtManager jwtManager;

    public JwtAuthenticationFilter(JwtManager jwtManager) {
        this.jwtManager = jwtManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("X-Auth-Token");
        if (StringUtils.isBlank(token)) {
            throw new JWTVerificationException("Invalid token");
        }
        JwtUser jwtUser = null;
        try {
            jwtUser = jwtManager.verify(token);
        } catch (JWTVerificationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        Set<SimpleGrantedAuthority> authorities = Optional.ofNullable(jwtUser.getRoles())
                .map(roles -> roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r)).collect(Collectors.toSet()))
                .orElse(null);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(jwtUser, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }
}

package com.fool3.common.admin.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityUtils {

    public static JwtUser getCurrentUser() {
        return (JwtUser) Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).map(Authentication::getPrincipal).orElse(null);
    }

    public static Long getCurrentUserId() {
        return Optional.ofNullable(getCurrentUser()).map(JwtUser::getId).orElse(null);
    }

}

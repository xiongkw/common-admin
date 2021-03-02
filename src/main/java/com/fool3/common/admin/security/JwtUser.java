package com.fool3.common.admin.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fool3.common.admin.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class JwtUser extends User {

    private Set<String> roles;

    public static JwtUser from(DecodedJWT jwt) {
        String subject = jwt.getSubject();
        JwtUser user = new JwtUser();
        user.setId(jwt.getClaim(JwtManager.KEY_USER_ID).asLong());
        user.setUsername(subject);
        String roleStr = jwt.getClaim(JwtManager.KEY_ROLES).asString();
        if (StringUtils.isNotEmpty(roleStr)) {
            Set<String> roles = new HashSet<>(Arrays.asList(roleStr.split(",")));
            user.setRoles(roles);
        }
        return user;
    }

    public static JwtUser from(User user) {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setId(user.getId());
        jwtUser.setUsername(user.getUsername());
        return jwtUser;
    }
}

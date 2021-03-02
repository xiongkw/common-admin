package com.fool3.common.admin.security;

import com.fool3.common.admin.common.Response;
import com.fool3.common.admin.entity.User;
import com.fool3.common.admin.exception.SecurityException;
import com.fool3.common.admin.service.IRoleService;
import com.fool3.common.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class AuthController {
    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth/token")
    public Response<String> token(@RequestParam String username, @RequestParam String password) {
        JwtUser jwtUser = verifyUser(username, password, true);
        String sign = jwtManager.sign(jwtUser);
        return Response.success(sign);
    }

    private JwtUser verifyUser(String username, String password, boolean checkPassword) {
        User user = userService.getByUsername(username);
        if (user == null) {
            throw SecurityException.AUTH_FAILED_USER_NOT_FOUND.exception(username);
        }
        if (!user.getEnabled()) {
            throw SecurityException.AUTH_FAILED_USER_NOT_ENABLE.exception(username);
        }
        if (checkPassword && !passwordEncoder.matches(password, user.getPassword())) {
            throw SecurityException.AUTH_FAILED_INVALID_PASSWORD.exception(username);
        }
        Set<String> roles = roleService.selectRoleSetByUserId(user.getId());
        JwtUser jwtUser = JwtUser.from(user);
        jwtUser.setRoles(roles);
        return jwtUser;
    }

    @PostMapping("/auth/token/refresh")
    public Response<String> token(@RequestParam String token) {
        JwtUser tUser = jwtManager.verifyForRefresh(token);
        JwtUser jwtUser = verifyUser(tUser.getUsername(), null, false);
        String sign = jwtManager.sign(jwtUser);
        return Response.success(sign);
    }

}

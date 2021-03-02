package com.fool3.common.admin.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Set;

@Component
public class JwtManager {
    public static final String KEY_EXP_AT = "e";
    public static final String KEY_USER_ID = "i";
    public static final String KEY_ROLES = "r";
    @Value("${security.jwt.secret:com.fool3}")
    private String jwtSecret;
    @Value("${security.jwt.issuer:com.fool3}")
    private String jwtIssuer;
    @Value("${security.jwt.expiresInMins:120}")
    private int jwtExpiresInMins;
    @Value("${security.jwt.refreshExpiresInMins:30}")
    private int jwtRefreshExpiresInMins;

    private JWTVerifier verifier;

    @PostConstruct
    public void init() {
        this.verifier = JWT.require(Algorithm.HMAC256(jwtSecret)).withIssuer(jwtIssuer).build();
    }

    public JwtUser verify(String token) {
        DecodedJWT jwt = verifier.verify(token);
        Date expAt = jwt.getClaim(KEY_EXP_AT).asDate();
        if (expAt.before(new Date())) {
            throw new TokenExpiredException(String.format("The Token has expired on %s.", expAt));
        }
        return JwtUser.from(jwt);
    }

    public JwtUser verifyForRefresh(String token) {
        DecodedJWT jwt = verifier.verify(token);
        Date expAt = jwt.getClaim(KEY_EXP_AT).asDate();
        if (expAt.before(DateUtils.addMinutes(new Date(), jwtRefreshExpiresInMins))) {
            throw new TokenExpiredException(String.format("The Token has expired on %s.", expAt));
        }
        return JwtUser.from(jwt);
    }

    public String sign(JwtUser user) {
        Date expiresAt = DateUtils.addMinutes(new Date(), this.jwtExpiresInMins);
        JWTCreator.Builder builder = JWT.create().withIssuer(this.jwtIssuer)
                .withSubject(user.getUsername())
                .withClaim(KEY_USER_ID, user.getId())
                .withClaim(KEY_EXP_AT, expiresAt);
        Set<String> roles = user.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            builder.withClaim(KEY_ROLES, String.join(",", roles));
        }
        return builder.sign(Algorithm.HMAC256(this.jwtSecret));
    }
}

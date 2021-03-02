package com.fool3.common.admin.config;

import com.fool3.common.admin.security.JwtAuthenticationFilter;
import com.fool3.common.admin.security.JwtManager;
import com.fool3.common.admin.security.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${swagger.enabled}")
    private boolean swaggerEnabled;

    @Autowired
    private JwtManager jwtManager;

    @Override
    public void configure(WebSecurity web) throws Exception {
        WebSecurity.IgnoredRequestConfigurer cfg = web.ignoring().antMatchers("/auth/**");
        if (swaggerEnabled) {
            cfg.antMatchers("/swagger-ui/**")
                    .antMatchers("/swagger-resources/**")
                    .antMatchers("/v3/**")
                    .antMatchers("/webjars/**");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                //因为使用JWT，所以不需要HttpSession
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //OPTIONS请求全部放行
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //登录接口放行
                .antMatchers("/api/admin/**").hasRole(Roles.ADMIN.getCode())
                //其他接口全部接受验证
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtManager), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
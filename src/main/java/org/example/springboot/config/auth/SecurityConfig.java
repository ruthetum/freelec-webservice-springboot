package org.example.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable() // h2 console을 사용하기 위해 옵션들 비활성화
                .and()
                    .authorizeRequests() // url별 권한 관리, antMatchers: 권한 관리 대상 지정
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // /api/v1/** api는 USER 권한을 가진 사람만 접근
                    .anyRequest().authenticated() // 설정된 값 이외의 url, authenticated: 인증딘 사용자들에게만 허가
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 '/'으로 연결
                .and()
                    .oauth2Login() // OAuth 2 로그인 진입점
                        .userInfoEndpoint() // 로그인 성공 후 사용자 정보를 가져올 때 설정
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치
    }
}

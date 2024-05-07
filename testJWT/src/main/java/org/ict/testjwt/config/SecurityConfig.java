package org.ict.testjwt.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // 권한에 따라 허용하는 url 설정
        httpSecurity
                .authorizeRequests()    // 권한인증된 정보만
                .requestMatchers("/","btop3","ntop3").permitAll()   // 접근 허용  url
                .requestMatchers("/boards").permitAll()                 // 접근 허용  url
                .requestMatchers("/notices").permitAll()                // 접근 허용  url
                .requestMatchers("/members","login").permitAll()      // 접근 허용  url
                .anyRequest().authenticated()   // 나머지 주소 처리
                // .and()
                // .logout().logoutUrl("/logout")
                // .invalidateHttpSession(true)
                // .clearAuthentication(true)
                // .logoutSuccessHandler((request, response, authentication) -> {
                    // refreshToken (db or radis 에 저장되어 있음) 제거 처리함.
                    // response.sendRedirect("/");
                // })
                ;

        return httpSecurity.build();
    }
}

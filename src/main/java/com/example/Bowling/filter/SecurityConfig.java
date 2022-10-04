package com.example.Bowling.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration //Spring이 설정 파일 or Bean을 만들기 위함
@EnableWebSecurity //FilterChain자동 포함
@RequiredArgsConstructor //생성자 주입
public class SecurityConfig extends WebSecurityConfigurerAdapter { //WebSecurityConfigurerAdapter Override하기 위해 상속

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable(); //csrf방지
        http.cors(); //서로 출처가 다른 웹 애플리케이션에서 자원을 공유하는 것, react 연동시 proxy 설정

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//session사용 안하므로 STATELESS로 끄기

        http.authorizeRequests().antMatchers( "/user/check","/user/token/refresh","/category/**", "/chat/**","/test/subscribe/**", "/user/kakao/**", "/survey/**", "/quotationSubmit/**", "/matchedList/**", "/matchedgosulist/**", "/gosu/**", "/quotation/**", "/matchedstart","/matchedfinish").permitAll();
        http.authorizeRequests().antMatchers().hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(GET, "/user/**").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(POST, "/user/**").permitAll();
        http.authorizeRequests().antMatchers().hasAuthority("ROLE_GOSU");
        http.authorizeRequests().anyRequest().authenticated(); //나머지 리퀘스트들은 인증이 필요하다

        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class); //지정된 필터 앞에 커스텀 필터를 추가 하여 먼저 실행
    }
}

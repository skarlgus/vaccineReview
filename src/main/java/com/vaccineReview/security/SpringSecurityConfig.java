package com.vaccineReview.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    /*
     *  스프링 시큐리티 앞단 설정
     * */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/bootstrap/**");   //bootstrap 권한 해제
    }

    /*
    *  스프링 시큐리티 설정
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/login/**","/layout/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.oauth2Login()
                .loginPage("/layout/login")
                .defaultSuccessUrl("/",true).permitAll()
                .and()
                .logout()
                .logoutUrl("/logout") /* 로그아웃 url*/
                //.logoutSuccessUrl("/layout/login")
                .invalidateHttpSession(true) /*로그아웃시 세션 제거*/
                .deleteCookies("remember-me", "JSESSIONID") /*쿠키 제거*/
                .clearAuthentication(true) /*권한정보 제거*/
                .permitAll()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }

}

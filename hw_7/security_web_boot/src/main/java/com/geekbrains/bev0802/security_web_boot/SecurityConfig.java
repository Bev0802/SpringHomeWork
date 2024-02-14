package com.geekbrains.bev0802.security_web_boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * В методе configure(HttpSecurity http) настраивается фильтр безопасности.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public-data").authenticated() // Доступ для аутентифицированных пользователей
                .antMatchers("/private-data").hasRole("ADMIN") // Уточнение доступа для ADMIN к private-data
                .anyRequest().permitAll() // Остальные запросы доступны всем
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/login");
    }
}

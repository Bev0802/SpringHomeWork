package com.geekbrains.bev0802.security_web_boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * В методе configure(HttpSecurity http) настраивается фильтр безопасности.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()                                // Настройка авторизации запросов
                .antMatchers("/public-data").authenticated()      // Доступ для аутентифицированных пользователей
                .antMatchers("/private-data").hasRole("ADMIN")    // Уточнение доступа для ADMIN к private-data
                .and()                                              // Завершение настроек авторизации и начало настроек формы входа
                .formLogin()                                        // Настройка формы входа
                .loginPage("/login")                                // Страница входа
                .permitAll()                                        // Доступ для всех пользователей
                .loginProcessingUrl("/process_login")               // URL для обработки входа
                .defaultSuccessUrl("/private-data")                 // URL в случае успешного входа
                .failureUrl("/login?error")      // URL в случае ошибки входа
                .and()                                              // Завершение настроек формы входа и начало настроек выхода
                .logout()                                           // Настройка выхода
                .logoutSuccessUrl("/")                              // URL в случае успешного выхода
                .and()                                              // Завершение настроек выхода и начало настроек обработки исключений
                .exceptionHandling()                                // Настройка обработки исключений
                .accessDeniedPage("/access_denied");  // Страница доступа запрещена

    }
    /**
     * Бин UserDetailsService - сервис для входа в систему.
     * @return объект UserDetailsService
     */

    @Bean
    public UserDetailsService userDetailsService() {
        /*
        * Создаем объект InMemoryUserDetailsManager,
        * который представляет собой реализацию UserDetailsService,
        * которая предоставляет информацию о управляющую пользователями в памяти.
         */
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        /*
         * Создаем пользователя с именем "user", паролем "password" и ролью "USER".
         * Используется устаревший метод withDefaultPasswordEncoder() для шифрования пароля
         * (не рекомендуется к использованию в продакшене из-за низкой безопасности).
         */
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build());
        /*
         *  Создаем еще одного пользователя с именем "admin", паролем "admin" и ролью "ADMIN".
         *  Также используется устаревший метод withDefaultPasswordEncoder().
         */
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build());
        return manager;
    }
}

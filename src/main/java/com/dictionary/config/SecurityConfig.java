package com.dictionary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/index").permitAll()
                .and().authorizeRequests().antMatchers("/regiser").permitAll()
                .and().authorizeRequests().antMatchers("/logout").permitAll()
                .and().formLogin().loginPage("/login").usernameParameter("nickName").passwordParameter("password")
                .and().logout().logoutSuccessUrl("/logoutt")
                .and().authorizeRequests().antMatchers("/home").authenticated()
                .and().authorizeRequests().antMatchers("/schedule").authenticated()
                .and().authorizeRequests().antMatchers("/edit/...").authenticated()
                .and().authorizeRequests().antMatchers("/payment").authenticated();
        http.csrf().disable();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select USER_NAME, PASSWORD, ENABLED from TBL_USER where USER_NAME = ?")
                .authoritiesByUsernameQuery("select u.USER_NAME, r.ROLE " +
                        "from TBL_USER_ROLE r " +
                        "  JOIN TBL_USER u ON r.USER_ID_FK = u.USER_ID " +
                        "where u.USER_NAME = ?").passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

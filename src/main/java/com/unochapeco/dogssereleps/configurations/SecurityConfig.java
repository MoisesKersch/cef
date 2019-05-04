package com.unochapeco.dogssereleps.configurations;


import com.unochapeco.dogssereleps.components.DataBaseProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataBaseProvider authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/allowed/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/allowed/login")
                .failureUrl("/allowed/login?error")
                .defaultSuccessUrl("/app/dashboard", true).passwordParameter("senha").usernameParameter("email")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/allowed/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/allowed/login");

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/vendors/**");
    }
}

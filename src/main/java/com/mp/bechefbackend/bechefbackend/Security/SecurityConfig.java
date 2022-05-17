package com.mp.bechefbackend.bechefbackend.Security;
import com.mp.bechefbackend.bechefbackend.Services.ServicesImpl.UserSecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserSecurityServiceImpl userSecurityService;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/api/**", "/auth/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers("/dist/**","/img/**","jsMyEventsApp/**","/plugins/**", "/error", "/soporte").permitAll()
                //.antMatchers("/login").permitAll()
                .antMatchers("/**").hasAuthority("ADMIN")//.permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/", true).permitAll()
                .and().logout().logoutUrl("/cerrarSesion").permitAll();
        http.exceptionHandling().accessDeniedPage("/login?noAcceso");
    }

}
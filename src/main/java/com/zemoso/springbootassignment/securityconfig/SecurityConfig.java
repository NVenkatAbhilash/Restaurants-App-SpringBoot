package com.zemoso.springbootassignment.securityconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/*
	 * Provides a convenient base class for creating a WebSecurityConfigurer instance.
	 *  The implementation allows customization by overriding methods.
	 */
	
    @Autowired
    private DataSource securityDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(securityDataSource);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/restaurants/showForm*").hasAnyRole("INSPECTOR","VIP")
			.antMatchers("/restaurants/save*").hasAnyRole("INSPECTOR","VIP")
			.antMatchers("/restaurants/delete*").hasRole("INSPECTOR")
			.antMatchers("/restaurants/list").hasAnyRole("VIP", "INSPECTOR","CUSTOMER")
			.antMatchers("/restaurants/review**").hasAnyRole("VIP", "INSPECTOR","CUSTOMER")
			.antMatchers("/api/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}
	
	
	
}

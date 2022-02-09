package com.spring.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsServiceImpl getDetails() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder getEncodedPassWord() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authenticate = new DaoAuthenticationProvider();
		authenticate.setUserDetailsService(getDetails());
		authenticate.setPasswordEncoder(getEncodedPassWord());
		return authenticate;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
		antMatchers("/registration","/forgot_password","/send_otp","/change_pass","/verify_otp").permitAll()
		.antMatchers("/edit/**").hasAnyAuthority("Admin")
		.antMatchers("/delete/**").hasAnyAuthority("Admin")
		.anyRequest().authenticated().
		and()
		.formLogin()
		.permitAll().loginPage("/login")
		.usernameParameter("email").passwordParameter("password")
		.loginProcessingUrl("/doLogin").defaultSuccessUrl("/homePage")
		.and()
		.logout()
		.invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll();
	}

}

package com.doctors.athome.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.doctors.athome.service.MongoUserDetailsService;

@Configuration
@EnableConfigurationProperties
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	MongoUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		

		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/api/dah/v0/**/health-reports").hasAuthority("admin")
		.antMatchers(HttpMethod.POST, "/api/dah/v0/clinician").hasAuthority("admin")
		.antMatchers(HttpMethod.GET, "/api/dah/v0/organization/organizations").hasAuthority("admin")
		.antMatchers(HttpMethod.POST, "/api/dah/v0/organization").hasAuthority("admin")
		.antMatchers(HttpMethod.GET, "/api/dah/v0/patient/patients").hasAuthority("admin")
		.antMatchers(HttpMethod.OPTIONS, "/api/dah/v0/**/health-reports").hasAuthority("admin")
		.antMatchers(HttpMethod.OPTIONS, "/api/dah/v0/clinician").hasAuthority("admin")
		.antMatchers(HttpMethod.OPTIONS, "/api/dah/v0/organization/organizations").hasAuthority("admin")
		.antMatchers(HttpMethod.OPTIONS, "/api/dah/v0/organization").hasAuthority("admin")
		.antMatchers(HttpMethod.OPTIONS, "/api/dah/v0/patient/patients").hasAuthority("admin")
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.and()
		.sessionManagement().disable();
			
	}	

}

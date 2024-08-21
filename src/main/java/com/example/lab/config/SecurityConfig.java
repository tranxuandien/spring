package com.example.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfiguration {

	
	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	    manager.createUser(User.withUsername("user")
	      .password(bCryptPasswordEncoder.encode("userPass"))
	      .roles("USER")
	      .build());
	    manager.createUser(User.withUsername("admin")
	      .password(bCryptPasswordEncoder.encode("adminPass"))
	      .roles("USER", "ADMIN")
	      .build());
	    return manager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	    http
//	    .csrf(AbstractHttpConfigurer::disable)
//	      .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//	              authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//	                      .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//	                      .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//	                      .requestMatchers("/login/**").permitAll()
//	                      .anyRequest().authenticated())
//	      .httpBasic(Customizer.withDefaults())
	      http.authorizeHttpRequests((requests) -> requests
					.requestMatchers("/").permitAll()
					.anyRequest().authenticated()
				).formLogin(form->form.defaultSuccessUrl("/home").permitAll())
	      .logout(logout-> logout.permitAll());
//	      .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

	    return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
//	    return web -> web.ignoring().requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
	    return web -> web.ignoring().requestMatchers("/img/**", "/lib/**", "/favicon.ico");
	}
	
	@Bean
	public BCryptPasswordEncoder initBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

package com.example.lab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.lab.repository.user.UserRepository;
import com.example.lab.service.securityServices.AuthenticationFilter;
import com.example.lab.service.securityServices.JwtService;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfiguration {

	private AuthenticationFilter authenticationFilter;
	@Autowired
	private UserRepository userRepository;

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		this.authenticationFilter = new AuthenticationFilter(this.userDetailsService());
//		this.authenticationProvider = 
//	    http
//	    .csrf(AbstractHttpConfigurer::disable)
//	      .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//	              authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//	                      .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//	                      .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//	                      .requestMatchers("/login/**").permitAll()
//	                      .anyRequest().authenticated())
//	      .httpBasic(Customizer.withDefaults())
	      http
          .cors(AbstractHttpConfigurer::disable)
	      .csrf(AbstractHttpConfigurer::disable)
	      .authorizeHttpRequests((requests) -> requests
					.requestMatchers("/beans","/index","/api/auth/**").permitAll()
					.anyRequest().authenticated()
				)
	      .formLogin(form->form
						.loginPage("/login")
						.defaultSuccessUrl("/index").permitAll())
	      .logout(logout-> logout.permitAll())
	      .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	      .authenticationProvider(this.authenticationProvider())
          .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
//	    return web -> web.ignoring().requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
	    return web -> web.ignoring().requestMatchers("/img/**", "/lib/**", "/favicon.ico","/css/**", "/js/**");
	}
	
//	@Bean
//	public BCryptPasswordEncoder initBCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}

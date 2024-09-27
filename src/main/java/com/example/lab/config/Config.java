package com.example.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.lab.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Config implements WebMvcConfigurer {

	//config
	public static final String CORS_DOMAIN="https://lab-repo.vercel.app/";
	private final UserRepository userRepository;
//	@Bean(name = "messageSource")
//	public MessageSource getMessageResource() {
//		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
//
//		messageResource.setBasename("i18n/messages");
//		messageResource.setDefaultEncoding("UTF-8");
//		return messageResource;
//	}
	@Bean()
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
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(CORS_DOMAIN).allowedMethods("POST","GET","DELETE","PUT");
    }
	
	
}
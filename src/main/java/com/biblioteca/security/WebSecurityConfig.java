package com.biblioteca.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.biblioteca.security.jwt.AuthEntryPointJwt;
import com.biblioteca.security.jwt.AuthFilterToken;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public AuthFilterToken authFilterToken() {
		return new AuthFilterToken();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors(Customizer.withDefaults());
		http.csrf(csrf -> csrf.disable())
				.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/v3/api-docs/**").permitAll()
						.requestMatchers("/swagger-ui/**").permitAll().requestMatchers("/swagger-ui.html").permitAll()
						.requestMatchers("/auth/**").permitAll().requestMatchers(HttpMethod.POST, "/usuarios/**")
						.permitAll().requestMatchers(HttpMethod.GET, "/usuarios/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/usuarios/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/usuarios/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/livros/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/livros/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/livros/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/livros/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/editoras/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/editoras/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/editoras/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/editoras/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/autores/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/autores/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/autores/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/autores/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/areas/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/areas/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/areas/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/areas/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/emprestimos/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/emprestimos/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/emprestimos/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/emprestimos/**").hasRole("ADMIN").anyRequest()
						.authenticated());

		http.addFilterBefore(authFilterToken(), UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

}

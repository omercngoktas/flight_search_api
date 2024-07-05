package com.omercangoktas.flight_search_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableWebSecurity
@EnableScheduling
public class SecurityConfig {

        @Bean
        public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("adminpass"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.DELETE, "/airports/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/airports/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/airports/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/flights/search").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .defaultSuccessUrl("/", true) // Giriş başarılı olduğunda ana sayfaya yönlendir
                        .permitAll()
                )
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.disable()) // CSRF korumasını devre dışı bırakın
                .sessionManagement(session -> session
                        .invalidSessionUrl("/login?invalid-session=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
        }

}
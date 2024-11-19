package com.safa.enviovital.security.config;

import com.safa.enviovital.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    //        @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//
//                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                        .requestMatchers("/auth/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/almacenes/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/provincias/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/evento/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/almacenes/**","/conductores/").permitAll()
//                        .requestMatchers("/conductores/**").hasRole("CONDUCTOR")
//                        .requestMatchers("/evento/**", "/almacenes/**",
//                                "/conductores/**", "/vehiculo/**").hasRole("ADMIN")
//                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
//                        .anyRequest().authenticated()
//
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//              return   http.build();
//    }
//}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/conductores/**", "/almacenes/**", "/auth/**", "/evento/**",
                                "/provincias/**","/usuarios/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
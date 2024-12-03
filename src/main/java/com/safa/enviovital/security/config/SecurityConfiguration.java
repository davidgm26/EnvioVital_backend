package com.safa.enviovital.security.config;

import com.safa.enviovital.enumerados.Rol;
import com.safa.enviovital.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/almacenes/**","/conductores/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/provincias/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/evento/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/almacenes/**", "conductores/**","/evento/**").hasAuthority(Rol.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/evento/**").hasAuthority(Rol.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/evento/**").hasAuthority(Rol.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "conductores/**").hasAnyAuthority(Rol.ADMIN.name(),Rol.CONDUCTOR.name())
                        .requestMatchers("/conductores/editar/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/almacenes/**", "/conductores/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/almacenes/**", "conductores/**").permitAll()
                        .requestMatchers("/conductores/editar/","/tiposVehiculo/**","/vehiculos/**","/provincias/**","/almacenes/guardar", "/conductores/guardar", "conductores/vehiculosRegistrados/**").permitAll()
                        .requestMatchers("/usuarios/**").permitAll()
                        .requestMatchers("/swagger/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Orígenes permitidos
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type")); // Cabeceras permitidas
        configuration.setAllowCredentials(true); // Permitir credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//
//                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                        .requestMatchers("/conductores/**", "/almacenes/**", "/auth/**", "/evento/**",
//                                "/provincias/**","/usuarios/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//}
//}

/*

  @Bean
    public HttpFirewall allowMaliciousCharactersFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedPercent(true); // Allow encoded characters
        firewall.setAllowUrlEncodedSlash(true);  // Allow %2F (optional)
        firewall.setAllowBackSlash(true);        // Allow \
        firewall.setAllowSemicolon(true);        // Allow ; in URLs
        firewall.setAllowUrlEncodedPeriod(true); // Allow %2E for periods
        firewall.setAllowUrlEncodedDoubleSlash(true); // Allow %2F%2F
        return firewall;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // Enable CORS with default settings
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/conductores/**", "/almacenes/**", "/auth/**", "/evento/**",
                                "/provincias/**", "/usuarios/**", "/tiposVehiculo/**", "/vehiculos/**").permitAll()
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
*/

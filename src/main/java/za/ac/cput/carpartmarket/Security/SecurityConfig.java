package za.ac.cput.carpartmarket.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // ---------- Always allowed ----------
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/error").permitAll()

                        // ---------- Auth ----------
                        .requestMatchers(HttpMethod.POST,
                                "/api/auth/login",
                                "/api/auth/register/buyer",
                                "/api/auth/register/seller",
                                "/api/auth/reset-password").permitAll()
                        // Only an admin can create another admin (the first one comes from AdminSeeder)
                        .requestMatchers(HttpMethod.POST, "/api/auth/register/admin").hasRole("ADMIN")

                        // ---------- Car parts: public browsing ----------
                        .requestMatchers(HttpMethod.GET, "/carparts/approved", "/carparts/read/**").permitAll()

                        // ---------- Car parts: admin ----------
                        .requestMatchers(HttpMethod.GET, "/carparts/getall", "/carparts/pending").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/carparts/*/approve", "/carparts/*/decline").hasRole("ADMIN")

                        // ---------- Car parts: sellers ----------
                        .requestMatchers(HttpMethod.POST, "/carparts/create").hasRole("SELLER")
                        .requestMatchers(HttpMethod.GET, "/carparts/seller/**").hasAnyRole("SELLER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/carparts/update").hasAnyRole("SELLER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/carparts/delete/**").hasAnyRole("SELLER", "ADMIN")

                        // ---------- Orders ----------
                        .requestMatchers(HttpMethod.POST, "/orders").hasRole("BUYER")
                        .requestMatchers(HttpMethod.GET, "/orders/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/orders").hasAnyRole("SELLER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/orders/**").hasRole("ADMIN")

                        // ---------- Categories: anyone reads, admins change ----------
                        .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                        .requestMatchers("/api/categories/**").hasRole("ADMIN")

                        // ---------- Back-office data: admins only ----------
                        .requestMatchers(
                                "/api/admins/**",
                                "/buyers/**",
                                "/sellers/**",
                                "/registers/**",
                                "/roles/**",
                                "/login/**",
                                "/transactions/**",
                                "/Payments/**",
                                "/delivery/**",
                                "/vehicles/**").hasRole("ADMIN")

                        // ---------- Anything else: must be logged in ----------
                        .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e.authenticationEntryPoint(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
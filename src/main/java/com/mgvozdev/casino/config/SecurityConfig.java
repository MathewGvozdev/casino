package com.mgvozdev.casino.config;

import com.mgvozdev.casino.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyAuthoritiesMapper;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.mgvozdev.casino.util.AuthorityRoleList.*;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserServiceImpl userDetailsService;
    private final RoleHierarchyImpl roleHierarchy;

    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        .requestMatchers(SUPERVISOR_LIST).hasRole(SUPERVISOR)
                        .requestMatchers(HOST_LIST).hasRole(HOST)
                        .requestMatchers(PIT_BOSS_LIST).hasRole(PIT_BOSS)
                        .requestMatchers(SHIFT_MANAGER_LIST).hasRole(SHIFT_MANAGER)
                        .requestMatchers("/users/password").authenticated()
                        .requestMatchers(ADMIN_LIST).hasRole(ADMIN)
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .logout(logout -> logout
                        .deleteCookies("JSESSIONID"))
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public RoleHierarchyAuthoritiesMapper authoritiesMapper() {
        return new RoleHierarchyAuthoritiesMapper(roleHierarchy);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

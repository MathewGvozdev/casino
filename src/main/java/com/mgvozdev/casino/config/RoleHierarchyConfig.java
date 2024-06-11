package com.mgvozdev.casino.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

@Configuration
public class RoleHierarchyConfig {

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = """
                ROLE_ADMIN > ROLE_SHIFT_MANAGER\s
                ROLE_SHIFT_MANAGER > ROLE_PIT_BOSS\s
                ROLE_PIT_BOSS > ROLE_SUPERVISOR""";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
}

package com.mgvozdev.casino.util;

public class AuthorityRoleList {

    public static final String SUPERVISOR = "SUPERVISOR";
    public static final String PIT_BOSS = "PIT_BOSS";
    public static final String SHIFT_MANAGER = "SHIFT_MANAGER";
    public static final String HOST = "HOST";
    public static final String ADMIN = "ADMIN";

    public static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    public static final String[] SUPERVISOR_LIST = {
            "/players/**",
            "/profiles/**",
            "/tables/**"
    };

    public static final String[] PIT_BOSS_LIST = {
            "/players/**",
            "/profiles/**",
            "/tables/**",
            "/reports/**"
    };

    public static final String[] SHIFT_MANAGER_LIST = {
            "/players/**",
            "/profiles/**",
            "/tables/**",
            "/reports/**",
            "/rewards/**"
    };

    public static final String[] HOST_LIST = {
            "/profiles/**",
            "/rewards/**",
            "/reports/**"
    };

    public static final String[] ADMIN_LIST = {
            "/players/**",
            "/profiles/**",
            "/tables/**",
            "/reports/**",
            "/rewards/**",
            "/users/**"
    };
}

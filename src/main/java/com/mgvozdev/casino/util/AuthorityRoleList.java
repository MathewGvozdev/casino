package com.mgvozdev.casino.util;

public class AuthorityRoleList {

    public static final String SUPERVISOR = "SUPERVISOR";
    public static final String PIT_BOSS = "PIT_BOSS";
    public static final String SHIFT_MANAGER = "SHIFT_MANAGER";
    public static final String HOST = "HOST";
    public static final String ADMIN = "ADMIN";

    public static final String[] ADMIN_LIST = {
//            "/v3/api-docs/**",
//            "/swagger-ui/**",
            "/users/**",
            "/players/**",
            "/players",
            "/profiles/**",
            "/tables/**"
    };

    public static final String[] HOST_LIST = {
//            "/v3/api-docs/**",
//            "/swagger-ui/**",
            "/users/**",
            "/profiles/**",
            "/rewards/**",
            "/reports/**"
    };

    public static final String[] SHIFT_MANAGER_LIST = {
//            "/v3/api-docs/**",
//            "/swagger-ui/**",
            "/players/**",
            "/profiles/**",
            "/rewards/**",
            "/reports/**",
            "/tables/**"
    };

    public static final String[] PIT_BOSS_LIST = {
//            "/v3/api-docs/**",
//            "/swagger-ui/**",
            "/players/**",
            "/profiles/**",
            "/reports/**",
            "/tables/**"
    };

    public static final String[] SUPERVISOR_LIST = {
//            "/v3/api-docs/**",
//            "/swagger-ui/**",
            "/players/**",
            "/profiles/**",
            "/tables/**"
    };
}

package com.mgvozdev.casino.annotation;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
@Sql("classpath:/db/test-schema.sql")
@Sql("classpath:/db/test-data.sql")
public @interface IntegrationTest {
}

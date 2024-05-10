package com.yologger.sns.api.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.DependsOn

@TestConfiguration
class DataSourceConfig {

    @Bean
    @DependsOn("TestMySQLContainer")
    fun dataSource(): HikariDataSource {
        return DataSourceBuilder.create()
            .type(HikariDataSource::class.java)
            .url(TestMySQLContainer.container.jdbcUrl)
            .username(TestMySQLContainer.container.username)
            .password(TestMySQLContainer.container.password)
            .build()
    }
}
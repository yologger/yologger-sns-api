package com.yologger.sns.api.repository

import com.yologger.sns.api.config.DataSourceConfig
import com.yologger.sns.api.config.TestMySQLContainer
import com.yologger.sns.api.config.database.PersistentConfig
import com.yologger.sns.api.config.database.QueryDslConfig
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest(showSql = true)
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Disable H2 on DataJpaTest
@Import(TestMySQLContainer::class, DataSourceConfig::class, PersistentConfig::class, QueryDslConfig::class)
abstract class AbstractDataJpaTest {

}
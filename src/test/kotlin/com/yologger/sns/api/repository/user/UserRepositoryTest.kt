package com.yologger.sns.api.repository.user

import com.yologger.sns.api.config.DataSourceConfig
import com.yologger.sns.api.config.TestMySQLContainer
import com.yologger.sns.api.config.database.PersistentConfig
import com.yologger.sns.api.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Disable H2 on DataJpaTest
@Import(TestMySQLContainer::class, DataSourceConfig::class, PersistentConfig::class)
class UserRepositoryTest(
    @Autowired private val userRepository: UserRepository
) {
    @Test
    @DisplayName("User 단건 추가")
    fun addUser() {
        val email = "yologger1013@gmail.com"

        userRepository.save(User(
            email = email,
            name = "yologger1013",
            nickname = "yologger",
            password = "12341234"
        ))

        val user = userRepository.findByEmail(email)
        assertThat(user.get().email).isEqualTo(email)
    }

    @Test
    @DisplayName("User 벌크 조회")
    @Sql(scripts = ["/sql/repository/insert_bulk_users.sql"])
    fun findUsers() {
        val users = userRepository.findAll()
        println(users)
        assertThat(users.size).isGreaterThanOrEqualTo(5)
    }
}
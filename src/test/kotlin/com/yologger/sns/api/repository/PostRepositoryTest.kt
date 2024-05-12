package com.yologger.sns.api.repository

import com.yologger.sns.api.config.DataSourceConfig
import com.yologger.sns.api.config.TestMySQLContainer
import com.yologger.sns.api.config.database.PersistentConfig
import com.yologger.sns.api.config.database.QueryDslConfig
import com.yologger.sns.api.infrastructure.entity.Post
import com.yologger.sns.api.infrastructure.repository.PostRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.testcontainers.junit.jupiter.Testcontainers

// @Disabled
@DataJpaTest(showSql = true)
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Disable H2 on DataJpaTest
@Import(TestMySQLContainer::class, DataSourceConfig::class, PersistentConfig::class, QueryDslConfig::class)
class PostRepositoryTest(
    @Autowired private val postRepository: PostRepository
) {
    @Test
    @DisplayName("Post 단건 추가")
    fun addPost() {

        // Given
        val uid = 1L
        val title = "제목입니다."
        val body = "본문입니다."

        // When
        val saved = postRepository.save(
            Post(
            uid = uid,
            title = title,
            body = body
        )
        )

        val post = postRepository.findById(saved.id)

        assertThat(saved.title).isEqualTo(title)
        assertThat(post.get().title).isEqualTo(title)
    }

    @Test
    @Sql(scripts = ["/sql/repository/insert_bulk_users.sql", "/sql/repository/insert_bulk_post.sql"])
    fun `user의 posts 조회 `() {
        val size = 5L
        val posts = postRepository.findPostsByUidOrderByCreateDateDesc(uid = 1, page = 0, size = size);
        assertThat(posts.size).isEqualTo(size)
    }
}
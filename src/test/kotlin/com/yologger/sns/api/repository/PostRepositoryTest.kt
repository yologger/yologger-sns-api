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
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.testcontainers.junit.jupiter.Testcontainers

// @Disabled
@Sql(scripts = ["/sql/repository/insert_bulk_users.sql", "/sql/repository/insert_bulk_post.sql"])
class PostRepositoryTest(
    @Autowired private val postRepository: PostRepository
): AbstractDataJpaTest() {
    @Test
    fun `Post 단건 추가`() {

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
    fun `Uid 기반 posts 조회 `() {
        val size = 5L
        val posts = postRepository.findPostsByUidOrderByCreateDateDesc(uid = 1, page = 0, size = size);
        assertThat(posts.size).isEqualTo(size)
    }

    @Test
    fun `최근 posts 조회`() {
        val size = 5
        val sort = Sort.by("id").descending()
        val pageable = PageRequest.of(0, size, sort)
        val posts = postRepository.findAll(pageable)
        assertThat(posts.size).isEqualTo(size)
    }


}
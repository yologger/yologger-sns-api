package com.yologger.sns.api.infrastructure.repository

import com.yologger.sns.api.infrastructure.entity.Post
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long>, PostCustomRepository {
}
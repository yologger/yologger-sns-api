package com.yologger.sns.api.repository

import com.yologger.sns.api.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long>
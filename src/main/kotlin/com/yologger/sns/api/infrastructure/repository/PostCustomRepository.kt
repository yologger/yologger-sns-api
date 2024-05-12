package com.yologger.sns.api.infrastructure.repository

import com.yologger.sns.api.infrastructure.entity.Post

interface PostCustomRepository {
    fun findPostsByUidOrderByCreateDateDesc(uid: Long, page: Long, size: Long): List<Post>
}
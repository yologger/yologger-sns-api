package com.yologger.sns.api.domain.post.dto

import com.yologger.sns.api.entity.Post
import java.time.LocalDateTime

data class PostData(
    val pid: Long,
    val uid: Long,
    val title: String,
    val body: String,
    val joinDate: LocalDateTime,
    val modifiedAt: LocalDateTime
) {
    companion object {
        fun fromEntity(post: Post): PostData {
            return PostData(
                pid = post.id,
                uid = post.uid,
                title = post.title,
                body = post.body,
                joinDate = post.joinDate,
                modifiedAt = post.modifiedAt
            )
        }
    }
}

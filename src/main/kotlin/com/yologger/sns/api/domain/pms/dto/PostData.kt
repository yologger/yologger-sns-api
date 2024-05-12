package com.yologger.sns.api.domain.pms.dto

import com.yologger.sns.api.infrastructure.entity.Post
import java.time.LocalDateTime

data class PostData(
    val pid: Long,
    val uid: Long,
    val title: String,
    val body: String,
    val createDate: LocalDateTime,
    val modifiedAt: LocalDateTime
) {
    companion object {
        fun fromEntity(post: Post): PostData {
            return PostData(
                pid = post.id,
                uid = post.uid,
                title = post.title,
                body = post.body,
                createDate = post.createDate,
                modifiedAt = post.modifiedAt
            )
        }
    }
}

package com.yologger.sns.api.domain.post.dto

import jakarta.validation.constraints.Positive

data class DeletePostRequest(
    @field:Positive
    val uid: Long,

    @field:Positive
    val pid: Long,
)

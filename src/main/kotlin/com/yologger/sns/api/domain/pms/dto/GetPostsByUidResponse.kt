package com.yologger.sns.api.domain.pms.dto

data class GetPostsByUidResponse(
    val size: Int,
    val posts: List<PostData>
)

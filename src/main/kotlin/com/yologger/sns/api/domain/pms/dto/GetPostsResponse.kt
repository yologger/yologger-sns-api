package com.yologger.sns.api.domain.pms.dto

data class GetPostsResponse(
    val size: Int,
    val posts: List<PostData>
)

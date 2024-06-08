package com.yologger.sns.api.domain.ums.dto

data class ValidateAccessTokenResponse(
    val uid: Long,
    val accessToken: String
)

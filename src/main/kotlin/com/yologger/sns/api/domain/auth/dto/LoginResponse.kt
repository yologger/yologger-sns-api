package com.yologger.sns.api.domain.auth.dto

data class LoginResponse(
    val uid: Long,
    val accessToken: String
)

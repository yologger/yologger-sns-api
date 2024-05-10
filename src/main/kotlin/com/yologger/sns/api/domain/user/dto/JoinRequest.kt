package com.yologger.sns.api.domain.user.dto

data class JoinRequest(
    val email: String,
    val name: String,
    val nickname: String,
    val password: String
)

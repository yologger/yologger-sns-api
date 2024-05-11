package com.yologger.sns.api.domain.auth

import com.yologger.sns.api.repository.user.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val accessTokenService: AccessTokenService
) {

    fun login() {

    }
}
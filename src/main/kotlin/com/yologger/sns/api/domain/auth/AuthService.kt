package com.yologger.sns.api.domain.auth

import com.yologger.sns.api.domain.auth.dto.AccessTokenClaim
import com.yologger.sns.api.domain.auth.dto.LoginResponse
import com.yologger.sns.api.domain.auth.exception.UserNotExistException
import com.yologger.sns.api.domain.auth.exception.WrongPasswordException
import com.yologger.sns.api.repository.user.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val accessTokenService: AccessTokenService,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun login(email: String, password: String) : LoginResponse {
        val user = userRepository.findByEmail(email)

        /** User doesn't exists */
        if (user.isEmpty) throw UserNotExistException("User not exists.")

        /** Wrong password */
        if (!passwordEncoder.matches(password, user.get().password)) throw WrongPasswordException("Wrong password.")

        /** Access token has already been issued */
        if (!user.get().accessToken.isNullOrBlank()) return LoginResponse(uid = user.get().id, accessToken = user.get().accessToken!!)

        /** Issue new access token */
        val accessToken = accessTokenService.generate(AccessTokenClaim(uid = user.get().id))
        user.get().accessToken = accessToken
        return LoginResponse(uid = user.get().id, accessToken = accessToken)
    }
}
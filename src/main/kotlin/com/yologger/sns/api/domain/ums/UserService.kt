package com.yologger.sns.api.domain.ums

import com.yologger.sns.api.domain.ums.dto.JoinRequest
import com.yologger.sns.api.domain.ums.dto.JoinResponse
import com.yologger.sns.api.domain.ums.exception.DuplicateUserException
import com.yologger.sns.api.infrastructure.entity.User
import com.yologger.sns.api.infrastructure.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    @Throws(DuplicateUserException::class)
    fun join(request: JoinRequest): JoinResponse {
        if (userRepository.findByEmail(request.email).isPresent) throw DuplicateUserException("User already exists.")
        val saved = userRepository.save(
            User(
                email = request.email,
                name = request.name,
                nickname = request.nickname,
                password = passwordEncoder.encode(request.password)
            )
        )
        return JoinResponse(uid = saved.id)
    }
}
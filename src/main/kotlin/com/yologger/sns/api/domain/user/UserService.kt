package com.yologger.sns.api.domain.user

import com.yologger.sns.api.domain.user.dto.JoinRequest
import com.yologger.sns.api.domain.user.dto.JoinResponse
import com.yologger.sns.api.domain.user.exception.UserAlreadyExistException
import com.yologger.sns.api.entity.User
import com.yologger.sns.api.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    @Throws(UserAlreadyExistException::class)
    fun join(request: JoinRequest): JoinResponse {
        if (userRepository.findByEmail(request.email).isPresent) throw UserAlreadyExistException("User already exists.")
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
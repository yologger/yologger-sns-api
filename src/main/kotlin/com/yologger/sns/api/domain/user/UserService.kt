package com.yologger.sns.api.domain.user

import com.yologger.sns.api.domain.user.dto.JoinRequest
import com.yologger.sns.api.domain.user.dto.JoinResponse
import com.yologger.sns.api.domain.user.exception.UserAlreadyExistException
import com.yologger.sns.api.entity.User
import com.yologger.sns.api.repository.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Throws(UserAlreadyExistException::class)
    fun join(request: JoinRequest): JoinResponse {
        if (userRepository.findByEmail(request.email).isPresent) throw UserAlreadyExistException("User already exists.")
        val saved = userRepository.save(
            User(
                email = request.email,
                name = request.name,
                nickname = request.nickname,
                password = request.password
            )
        )
        return JoinResponse(uid = saved.id)
    }
}
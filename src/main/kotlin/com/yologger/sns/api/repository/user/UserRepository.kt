package com.yologger.sns.api.repository.user

import com.yologger.sns.api.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

interface UserRepository: JpaRepository<User, Long> {

    @Transactional(readOnly = true)
    fun findByEmail(email: String): Optional<User>

}
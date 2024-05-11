package com.yologger.sns.api.domain.user

import any
import com.yologger.sns.api.domain.user.dto.JoinRequest
import com.yologger.sns.api.domain.user.exception.UserAlreadyExistException
import com.yologger.sns.api.entity.User
import com.yologger.sns.api.repository.user.UserRepository
import mock
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

class UserServiceTest {
    private val userRepository: UserRepository = mock()
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
    private val service = UserService(userRepository = userRepository, passwordEncoder = passwordEncoder)

    @Nested
    @DisplayName("회원가입")
    inner class JoinTest {

        @Test
        fun `회원가입에 성공했을 때`() {

            // Given
            val request = JoinRequest(
                email = "yologger1013@gmail.com",
                name = "yologger",
                nickname = "yologger",
                password = "1234"
            )

            given(
                userRepository.findByEmail(any())
            ).willReturn(
                Optional.empty()
            )

            given(
                userRepository.save(any())
            ).willReturn(
                User(
                    id = 1,
                    email = "yologger1013@gmail.com",
                    name = "yologger",
                    nickname = "yologger",
                    password = "1234",
                    accessToken = null
                )
            )

            // When, Then
            val response = service.join(request)
            assertThat(response.uid).isNotNull
        }

        @Test
        fun `이미 계정이 존재할 때`() {

            // Given
            val request = JoinRequest(
                email = "yologger1013@gmail.com",
                name = "yologger",
                nickname = "yologger",
                password = "1234"
            )

            given(
                userRepository.findByEmail(any())
            ).willReturn(
                Optional.of(User(
                    email = "yologger1013@gmail.com",
                    name = "yologger",
                    nickname = "yologger",
                    password = "1234"))
            )

            // When, Then
            assertThatThrownBy {
                service.join(request)
            }.isExactlyInstanceOf(UserAlreadyExistException::class.java)
        }
    }
}

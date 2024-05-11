package com.yologger.sns.api.rest.resource

import com.yologger.sns.api.config.MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE
import com.yologger.sns.api.domain.auth.AUTH_TOKEN_KEY
import com.yologger.sns.api.domain.auth.AuthService
import com.yologger.sns.api.domain.auth.dto.LoginRequest
import com.yologger.sns.api.rest.support.wrapCreated
import com.yologger.sns.api.rest.support.wrapOk
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth/v1", produces = [MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE])
class AuthResource(
    private val authService: AuthService
) {

    @PostMapping("/login", consumes = [APPLICATION_JSON_VALUE])
    fun login(@Validated @RequestBody request: LoginRequest) = authService.login(request.email, request.password).wrapOk()

    @PostMapping("/validate_token", consumes = [APPLICATION_JSON_VALUE])
    fun validateToken(
        @RequestHeader(AUTH_TOKEN_KEY, required = false) token: String?,
    ) {

    }

    @PostMapping("/logout", consumes = [APPLICATION_JSON_VALUE])
    fun logout() {

    }
}
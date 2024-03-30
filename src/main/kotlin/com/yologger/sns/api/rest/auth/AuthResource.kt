package com.yologger.sns.api.rest.auth

import com.yologger.sns.api.domain.auth.AUTH_TOKEN_KEY
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/v1")
class AuthResource {

    @PostMapping("/login", consumes = [APPLICATION_JSON_VALUE])
    fun login() {

    }

    @PostMapping("/validate_token", consumes = [APPLICATION_JSON_VALUE])
    fun validateToken(
        @RequestHeader(AUTH_TOKEN_KEY, required = false) token: String?,
    ) {

    }

    @PostMapping("/logout", consumes = [APPLICATION_JSON_VALUE])
    fun logout() {

    }
}
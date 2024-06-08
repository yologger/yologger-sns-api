package com.yologger.sns.api.rest.resource.ums

import com.yologger.sns.api.config.MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE
import com.yologger.sns.api.domain.ums.AUTH_TOKEN_KEY
import com.yologger.sns.api.domain.ums.AuthService
import com.yologger.sns.api.domain.ums.dto.AuthFailureResponse
import com.yologger.sns.api.domain.ums.dto.LoginRequest
import com.yologger.sns.api.domain.ums.dto.LogoutRequest
import com.yologger.sns.api.domain.ums.dto.ValidateAccessTokenRequest
import com.yologger.sns.api.domain.ums.exception.AuthException
import com.yologger.sns.api.domain.ums.exception.UserNotFoundException
import com.yologger.sns.api.rest.support.Response
import com.yologger.sns.api.rest.support.wrapConflict
import com.yologger.sns.api.rest.support.wrapOk
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/ums/v1/auth", produces = [MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE])
class UmsAuthResource(
    private val authService: AuthService
) {

    @PostMapping("/login", consumes = [APPLICATION_JSON_VALUE])
    fun login(@Validated @RequestBody request: LoginRequest) = authService.login(request.email, request.password).wrapOk()

    @PostMapping("/validate_token", consumes = [APPLICATION_JSON_VALUE])
    fun validateToken(@Validated @RequestBody request: ValidateAccessTokenRequest) = authService.validateToken(uid = request.uid, accessToken = request.accessToken).wrapOk()

    @PostMapping("/logout", consumes = [APPLICATION_JSON_VALUE])
    fun logout(
        @RequestHeader(AUTH_TOKEN_KEY, required = true) accessToken: String,
        @Validated @RequestBody request: LogoutRequest
    ) = authService.logout(uid = request.uid, accessToken = accessToken)

    @ExceptionHandler(value = [UserNotFoundException::class])
    fun handle(e: UserNotFoundException): ResponseEntity<Response<AuthFailureResponse>> {
        return AuthFailureResponse(message = e.message!!).wrapConflict()
    }

    @ExceptionHandler(value = [AuthException::class])
    fun handle(e: AuthException): ResponseEntity<Response<AuthFailureResponse>> {
        return AuthFailureResponse(message = e.message!!).wrapConflict()
    }
}
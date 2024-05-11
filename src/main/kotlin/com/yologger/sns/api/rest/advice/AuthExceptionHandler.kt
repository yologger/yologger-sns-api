package com.yologger.sns.api.rest.advice

import com.yologger.sns.api.domain.auth.dto.LoginFailureResponse
import com.yologger.sns.api.domain.auth.exception.UserNotExistException
import com.yologger.sns.api.domain.auth.exception.WrongPasswordException
import com.yologger.sns.api.rest.resource.AuthResource
import com.yologger.sns.api.rest.support.Response
import com.yologger.sns.api.rest.support.wrapBadRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [AuthResource::class])
class AuthExceptionHandler {

    @ExceptionHandler(value = [UserNotExistException::class])
    fun handle(e: UserNotExistException): ResponseEntity<Response<LoginFailureResponse>> {
        return LoginFailureResponse(message = e.message!!).wrapBadRequest()
    }

    @ExceptionHandler(value = [WrongPasswordException::class])
    fun handle(e: WrongPasswordException): ResponseEntity<Response<LoginFailureResponse>> {
        return LoginFailureResponse(message = e.message!!).wrapBadRequest()
    }
}
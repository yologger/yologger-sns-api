package com.yologger.sns.api.rest.advice

import com.yologger.sns.api.domain.user.dto.JoinFailureResponse
import com.yologger.sns.api.domain.user.exception.UserAlreadyExistException
import com.yologger.sns.api.rest.resource.UserResource
import com.yologger.sns.api.rest.support.Response
import com.yologger.sns.api.rest.support.wrapConflict
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

//@RestControllerAdvice(basePackageClasses = [UserResource::class])
//class UserExceptionHandler {
//
//    @ExceptionHandler(value = [UserAlreadyExistException::class])
//    fun handleUserAlreadyExistException(e: UserAlreadyExistException): ResponseEntity<Response<JoinFailureResponse>> {
//        return JoinFailureResponse(message = "User already exists").wrapConflict()
//    }
//}
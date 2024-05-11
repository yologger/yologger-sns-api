package com.yologger.sns.api.rest.advice

import com.yologger.sns.api.rest.response.GlobalErrorResponse
import com.yologger.sns.api.rest.support.Response
import com.yologger.sns.api.rest.support.wrapInternalServerError
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

//@RestControllerAdvice
//@Order(Ordered.LOWEST_PRECEDENCE)
//class GlobalExceptionHandler {
//
//    @ExceptionHandler(Exception::class)
//    fun handle(e: Exception): ResponseEntity<Response<GlobalErrorResponse>> {
//        return GlobalErrorResponse(message = "Internal Server Error").wrapInternalServerError()
//    }
//}
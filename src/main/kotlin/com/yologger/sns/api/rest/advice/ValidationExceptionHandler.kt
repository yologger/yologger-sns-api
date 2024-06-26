package com.yologger.sns.api.rest.advice

import com.yologger.sns.api.rest.response.GlobalErrorResponse
import com.yologger.sns.api.rest.support.Response
import com.yologger.sns.api.rest.support.wrapBadRequest
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1)
class ValidationExceptionHandler {

    private val logger = LoggerFactory.getLogger(ValidationExceptionHandler::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(e: MethodArgumentNotValidException): ResponseEntity<Response<GlobalErrorResponse>> {
        val fieldErrors = e.bindingResult.fieldErrors
        val fieldError = fieldErrors[fieldErrors.size - 1]
        val errorMessage  = fieldError.defaultMessage ?: "${fieldError.field} field has invalid value"
        return GlobalErrorResponse(message = errorMessage).wrapBadRequest()
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handle(e: HttpMessageNotReadableException): ResponseEntity<Response<GlobalErrorResponse>> {
        return GlobalErrorResponse(message = "JSON Parse error").wrapBadRequest()
    }

}
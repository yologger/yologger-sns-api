package com.yologger.sns.api.rest.advice

import com.yologger.sns.api.domain.auth.exception.UserNotExistException
import com.yologger.sns.api.domain.post.dto.CreatePostFailureResponse
import com.yologger.sns.api.domain.post.dto.EditPostFailureResponse
import com.yologger.sns.api.domain.post.exception.PostNotExistException
import com.yologger.sns.api.domain.post.exception.WrongPostWriterException
import com.yologger.sns.api.rest.resource.PostResource
import com.yologger.sns.api.rest.support.Response
import com.yologger.sns.api.rest.support.wrapBadRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

//@RestControllerAdvice(basePackageClasses = [PostResource::class])
//class PostExceptionHandler {
//
//    @ExceptionHandler(value = [UserNotExistException::class])
//    fun handle(e: UserNotExistException): ResponseEntity<Response<CreatePostFailureResponse>> {
//        return CreatePostFailureResponse(message = e.message!!).wrapBadRequest()
//    }
//
//    @ExceptionHandler(value = [PostNotExistException::class])
//    fun handle(e: PostNotExistException): ResponseEntity<Response<EditPostFailureResponse>> {
//        return EditPostFailureResponse(message = e.message!!).wrapBadRequest()
//    }
//
//    @ExceptionHandler(value = [WrongPostWriterException::class])
//    fun handle(e: WrongPostWriterException): ResponseEntity<Response<EditPostFailureResponse>> {
//        return EditPostFailureResponse(message = e.message!!).wrapBadRequest()
//    }
//}
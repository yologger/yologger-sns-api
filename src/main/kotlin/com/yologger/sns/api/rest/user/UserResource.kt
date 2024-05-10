package com.yologger.sns.api.rest.user

import com.yologger.sns.api.domain.user.UserService
import com.yologger.sns.api.domain.user.dto.JoinFailureResponse
import com.yologger.sns.api.domain.user.dto.JoinRequest
import com.yologger.sns.api.domain.user.exception.UserAlreadyExistException
import com.yologger.sns.api.rest.support.Response
import com.yologger.sns.api.rest.support.wrapConflict
import com.yologger.sns.api.rest.support.wrapCreated
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.yologger.sns.api.config.MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE

@RestController
@RequestMapping("/api/user/v1", produces = [MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE])
class UserResource(
    private val userService: UserService
) {

    @PostMapping("/join", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun join(@RequestBody request: JoinRequest) = userService.join(request).wrapCreated()

    @ExceptionHandler(value = [UserAlreadyExistException::class])
    fun handleUserAlreadyExistException(e: UserAlreadyExistException): ResponseEntity<Response<JoinFailureResponse>> {
        return JoinFailureResponse(message = "User already exists").wrapConflict()
    }

    @DeleteMapping("/withdraw", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun withdraw() {

    }

    @GetMapping("/{uid}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getUser(@PathVariable(name = "uid") uid: Long) {

    }
}
package com.yologger.sns.api.rest.resource.ums

import com.yologger.sns.api.domain.ums.UserService
import com.yologger.sns.api.domain.ums.dto.JoinFailureResponse
import com.yologger.sns.api.domain.ums.dto.JoinRequest
import com.yologger.sns.api.domain.ums.exception.DuplicateUserException
import com.yologger.sns.api.rest.support.Response
import com.yologger.sns.api.rest.support.wrapConflict
import com.yologger.sns.api.rest.support.wrapCreated
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.yologger.sns.api.config.MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE
import org.springframework.validation.annotation.Validated

@RestController
@RequestMapping("/api/ums/v1", produces = [MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE])
class UmsResource(
    private val userService: UserService
) {

    @PostMapping("/join", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun join(@Validated @RequestBody request: JoinRequest) = userService.join(request).wrapCreated()

    @DeleteMapping("/withdraw", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun withdraw() {

    }

    @GetMapping("/user/{uid}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getUser(@PathVariable(name = "uid") uid: Long) {

    }

    @ExceptionHandler(value = [DuplicateUserException::class])
    fun handleUserAlreadyExistException(e: DuplicateUserException): ResponseEntity<Response<JoinFailureResponse>> {
        return JoinFailureResponse(message = "User already exists").wrapConflict()
    }
}
package com.yologger.sns.api.rest.resource.pms

import com.yologger.sns.api.config.MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE
import com.yologger.sns.api.domain.ums.exception.UserNotFoundException
import com.yologger.sns.api.domain.pms.PostService
import com.yologger.sns.api.domain.pms.dto.*
import com.yologger.sns.api.domain.pms.exception.PostNotFoundException
import com.yologger.sns.api.domain.pms.exception.WrongPostWriterException
import com.yologger.sns.api.rest.support.Response
import com.yologger.sns.api.rest.support.wrapBadRequest
import com.yologger.sns.api.rest.support.wrapCreated
import com.yologger.sns.api.rest.support.wrapOk
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pms/v1", produces = [MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE])
class PmsResource(
    private val postService: PostService
) {

    @PostMapping("/post", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createPost(@Validated @RequestBody request: CreatePostRequest) = postService.createPost(uid = request.uid, title = request.title, body = request.body).wrapCreated()

    @PatchMapping("/post")
    fun editPost(@Validated @RequestBody request: EditPostRequest) = postService.editPost(uid = request.uid, pid = request.pid, newTitle = request.title, newBody = request.body).wrapOk()

    @DeleteMapping("/post", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun deletePost(@Validated @RequestBody request: DeletePostRequest) = postService.deletePost(uid = request.uid, pid = request.pid).wrapOk()

    @GetMapping("/post/{pid}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getPost(@PathVariable(name = "pid") pid: Long) = postService.getPost(pid).wrapOk()

    @GetMapping("/posts", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getPosts() {

    }

    @ExceptionHandler(value = [UserNotFoundException::class])
    fun handle(e: UserNotFoundException): ResponseEntity<Response<PmsFailureResponse>> {
        return PmsFailureResponse(message = e.message!!).wrapBadRequest()
    }

    @ExceptionHandler(value = [PostNotFoundException::class])
    fun handle(e: PostNotFoundException): ResponseEntity<Response<PmsFailureResponse>> {
        return PmsFailureResponse(message = e.message!!).wrapBadRequest()
    }

    @ExceptionHandler(value = [WrongPostWriterException::class])
    fun handle(e: WrongPostWriterException): ResponseEntity<Response<PmsFailureResponse>> {
        return PmsFailureResponse(message = e.message!!).wrapBadRequest()
    }
}
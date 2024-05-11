package com.yologger.sns.api.rest.resource

import com.yologger.sns.api.config.MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE
import com.yologger.sns.api.domain.auth.exception.UserNotExistException
import com.yologger.sns.api.domain.post.PostService
import com.yologger.sns.api.domain.post.dto.CreatePostFailureResponse
import com.yologger.sns.api.domain.post.dto.CreatePostRequest
import com.yologger.sns.api.domain.post.dto.EditPostFailureResponse
import com.yologger.sns.api.domain.post.dto.EditPostRequest
import com.yologger.sns.api.domain.post.exception.PostNotExistException
import com.yologger.sns.api.domain.post.exception.WrongPostWriterException
import com.yologger.sns.api.rest.support.Response
import com.yologger.sns.api.rest.support.wrapBadRequest
import com.yologger.sns.api.rest.support.wrapCreated
import com.yologger.sns.api.rest.support.wrapOk
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post/v1", produces = [MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE])
class PostResource(
    private val postService: PostService
) {

    @PostMapping("/post", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createPost(@Validated @RequestBody request: CreatePostRequest) = postService.createPost(uid = request.uid, title = request.title, body = request.body).wrapCreated()

    @PatchMapping("/post")
    fun editPost(@Validated @RequestBody request: EditPostRequest) = postService.editPost(uid = request.uid, pid = request.pid, newTitle = request.title, newBody = request.body).wrapOk()

    @GetMapping("/posts", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getPosts() {

    }

    @GetMapping("/post/{pid}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getPost(@PathVariable(name = "pid") pid: Long) {
    }

    @DeleteMapping("/post/{pid}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun deletePost(@PathVariable(name = "pid") pid: Long) {

    }

    @ExceptionHandler(value = [UserNotExistException::class])
    fun handle(e: UserNotExistException): ResponseEntity<Response<CreatePostFailureResponse>> {
        return CreatePostFailureResponse(message = e.message!!).wrapBadRequest()
    }

    @ExceptionHandler(value = [PostNotExistException::class])
    fun handle(e: PostNotExistException): ResponseEntity<Response<EditPostFailureResponse>> {
        return EditPostFailureResponse(message = e.message!!).wrapBadRequest()
    }

    @ExceptionHandler(value = [WrongPostWriterException::class])
    fun handle(e: WrongPostWriterException): ResponseEntity<Response<EditPostFailureResponse>> {
        return EditPostFailureResponse(message = e.message!!).wrapBadRequest()
    }
}
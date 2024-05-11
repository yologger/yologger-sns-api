package com.yologger.sns.api.rest.resource

import com.yologger.sns.api.config.MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE
import com.yologger.sns.api.domain.post.PostService
import com.yologger.sns.api.domain.post.dto.CreatePostRequest
import com.yologger.sns.api.rest.support.wrapCreated
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post/v1", produces = [MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE])
class PostResource(
    private val postService: PostService
) {

    @PostMapping("/post", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createPost(@Validated @RequestBody request: CreatePostRequest) = postService.createPost(uid = request.uid, title = request.title, body = request.body).wrapCreated()

    @GetMapping("/posts", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getPosts() {

    }

    @GetMapping("/post/{pid}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getPost(@PathVariable(name = "pid") pid: Long) {

    }

    @DeleteMapping("/post/{pid}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun deletePost(@PathVariable(name = "pid") pid: Long) {

    }
}
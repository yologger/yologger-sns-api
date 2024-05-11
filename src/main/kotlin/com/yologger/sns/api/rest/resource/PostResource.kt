package com.yologger.sns.api.rest.post

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post/v1")
class PostResource {

    @GetMapping("/posts", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getPosts() {

    }

    @GetMapping("/post/{pid}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getPost(@PathVariable(name = "pid") pid: Long) {

    }

    @PostMapping("/post", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createPost() {

    }

    @DeleteMapping("/post/{pid}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun deletePost(@PathVariable(name = "pid") pid: Long) {

    }
}
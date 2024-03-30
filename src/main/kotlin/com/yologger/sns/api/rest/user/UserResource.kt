package com.yologger.sns.api.rest.user

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user/v1")
class UserResource {

    @PostMapping("/join", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun join() {

    }

    @DeleteMapping("/withdraw", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun withdraw() {

    }

    @GetMapping("/{uid}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getUser(@PathVariable(name = "uid") uid: Long) {

    }
}
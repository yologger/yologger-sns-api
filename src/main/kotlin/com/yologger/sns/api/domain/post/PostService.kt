package com.yologger.sns.api.domain.post

import com.yologger.sns.api.domain.auth.exception.UserNotExistException
import com.yologger.sns.api.domain.post.dto.PostDTO
import com.yologger.sns.api.domain.user.exception.UserAlreadyExistException
import com.yologger.sns.api.entity.Post
import com.yologger.sns.api.repository.PostRepository
import com.yologger.sns.api.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) {
    @Transactional
    @Throws(UserNotExistException::class)
    fun createPost(uid: Long, title: String, body: String): PostDTO {
        if (!userRepository.existsById(uid)) throw UserNotExistException("User not exists.")
        val saved = postRepository.save(Post(
            uid = uid,
            title = title,
            body = body
        ))
        return PostDTO.fromEntity(post = saved)
    }

    @Transactional
    fun editPost(pid: Long, newTitle: String, newBody: String) {

    }

    @Transactional
    fun deletePost(pid: Long) {

    }

    @Transactional(readOnly = true)
    fun getPost(pid: Long) {

    }

    @Transactional(readOnly = true)
    fun getPosts() {

    }

    @Transactional(readOnly = true)
    fun getPostsByUid(uid: String) {

    }
}
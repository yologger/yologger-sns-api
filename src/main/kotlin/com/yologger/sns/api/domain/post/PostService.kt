package com.yologger.sns.api.domain.post

import com.yologger.sns.api.domain.auth.exception.UserNotExistException
import com.yologger.sns.api.domain.post.dto.DeletePostResponse
import com.yologger.sns.api.domain.post.dto.PostDTO
import com.yologger.sns.api.domain.post.exception.PostNotExistException
import com.yologger.sns.api.domain.post.exception.WrongPostWriterException
import com.yologger.sns.api.entity.Post
import com.yologger.sns.api.repository.PostRepository
import com.yologger.sns.api.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

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
    @Throws(UserNotExistException::class, PostNotExistException::class, WrongPostWriterException::class)
    fun editPost(uid: Long, pid: Long, newTitle: String, newBody: String): PostDTO {
        val post = validatePost(pid, uid)
        post.get().title = newTitle
        post.get().body = newBody
        return PostDTO.fromEntity(post.get())
    }

    @Transactional
    @Throws(UserNotExistException::class, PostNotExistException::class, WrongPostWriterException::class)
    fun deletePost(uid: Long, pid: Long): DeletePostResponse {
        validatePost(pid, uid)
        postRepository.deleteById(pid)
        return DeletePostResponse(uid = uid, pid = pid)
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

    private fun validatePost(pid: Long, uid: Long): Optional<Post> {
        if (!userRepository.existsById(uid)) throw UserNotExistException("User not exists.")
        val post = postRepository.findById(pid)
        if (post.isEmpty) throw PostNotExistException("Post not exists")
        if (post.get().uid != uid) throw WrongPostWriterException("Wrong post writer")
        return post;
    }
}
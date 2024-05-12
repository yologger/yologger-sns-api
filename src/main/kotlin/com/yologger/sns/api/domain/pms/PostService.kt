package com.yologger.sns.api.domain.pms

import com.yologger.sns.api.domain.ums.exception.UserNotFoundException
import com.yologger.sns.api.domain.pms.dto.DeletePostResponse
import com.yologger.sns.api.domain.pms.dto.GetPostsByUidResponse
import com.yologger.sns.api.domain.pms.dto.GetPostsResponse
import com.yologger.sns.api.domain.pms.dto.PostData
import com.yologger.sns.api.domain.pms.exception.PostNotFoundException
import com.yologger.sns.api.domain.pms.exception.WrongPostWriterException
import com.yologger.sns.api.infrastructure.entity.Post
import com.yologger.sns.api.infrastructure.repository.PostRepository
import com.yologger.sns.api.infrastructure.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class PostService(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) {
    @Transactional
    @Throws(UserNotFoundException::class)
    fun createPost(uid: Long, title: String, body: String): PostData {
        if (!userRepository.existsById(uid)) throw UserNotFoundException("User not found")
        val saved = postRepository.save(
            Post(
            uid = uid,
            title = title,
            body = body
        )
        )
        return PostData.fromEntity(post = saved)
    }

    @Transactional
    @Throws(UserNotFoundException::class, PostNotFoundException::class, WrongPostWriterException::class)
    fun editPost(uid: Long, pid: Long, newTitle: String, newBody: String): PostData {
        val post = validatePost(pid, uid)
        post.get().title = newTitle
        post.get().body = newBody
        return PostData.fromEntity(post.get())
    }

    @Transactional
    @Throws(UserNotFoundException::class, PostNotFoundException::class, WrongPostWriterException::class)
    fun deletePost(uid: Long, pid: Long): DeletePostResponse {
        validatePost(pid, uid)
        postRepository.deleteById(pid)
        return DeletePostResponse(uid = uid, pid = pid)
    }

    @Transactional(readOnly = true)
    fun getPost(pid: Long): PostData = PostData.fromEntity(postRepository.findById(pid).orElseThrow { PostNotFoundException("Post not found") }!!)

    @Transactional(readOnly = true)
    fun getPosts(page: Int, size: Int): GetPostsResponse {
        val posts = postRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending())).map { PostData.fromEntity(it) }.content
        return GetPostsResponse(size = posts.size, posts = posts)
    }

    @Transactional(readOnly = true)
    fun getPostsByUid(uid: Long, page: Long, size: Long): GetPostsByUidResponse {
        val posts = postRepository.findPostsByUidOrderByCreateDateDesc(uid = uid, page = page, size = size).map { PostData.fromEntity(it) }
        return GetPostsByUidResponse(size = posts.size, posts = posts)
    }

    @Throws(UserNotFoundException::class, PostNotFoundException::class, WrongPostWriterException::class)
    private fun validatePost(pid: Long, uid: Long): Optional<Post> {
        if (!userRepository.existsById(uid)) throw UserNotFoundException("User not found")
        val post = postRepository.findById(pid)
        if (post.isEmpty) throw PostNotFoundException("Post not found")
        if (post.get().uid != uid) throw WrongPostWriterException("Wrong post writer")
        return post;
    }
}
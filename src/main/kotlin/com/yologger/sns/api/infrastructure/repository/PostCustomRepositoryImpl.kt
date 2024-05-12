package com.yologger.sns.api.infrastructure.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.yologger.sns.api.infrastructure.entity.Post
import org.springframework.transaction.support.TransactionTemplate

import com.yologger.sns.api.infrastructure.entity.QPost

class PostCustomRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
    private val template: TransactionTemplate
): PostCustomRepository {

    private val qPost = QPost.post

    override fun findPostsByUidOrderByCreateDateDesc(uid: Long, page: Long, size: Long): List<Post> {
        return template.execute {
            jpaQueryFactory.selectFrom(qPost)
                .where(qPost.uid.eq(uid))
                .offset(page * size)
                .limit(size)
                .orderBy(qPost.id.desc())
                .fetch()
        } ?: emptyList()
    }
}
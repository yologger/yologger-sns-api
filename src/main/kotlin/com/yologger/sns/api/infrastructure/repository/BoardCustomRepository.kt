package com.yologger.sns.api.infrastructure.repository

import com.yologger.sns.api.infrastructure.entity.Board

interface BoardCustomRepository {
    fun findBoardsByUidOrderByCreateDateDesc(uid: Long, page: Long, size: Long): List<Board>
}
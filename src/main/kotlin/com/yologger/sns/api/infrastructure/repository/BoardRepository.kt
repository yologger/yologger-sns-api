package com.yologger.sns.api.infrastructure.repository

import com.yologger.sns.api.infrastructure.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long>, BoardCustomRepository {
}
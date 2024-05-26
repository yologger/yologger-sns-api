package com.yologger.sns.api.domain.bms.dto

import com.yologger.sns.api.infrastructure.entity.Board
import java.time.LocalDateTime

data class BoardData(
    val bid: Long,
    val uid: Long,
    val title: String,
    val body: String,
    val createDate: LocalDateTime,
    val modifiedAt: LocalDateTime
) {
    companion object {
        fun fromEntity(board: Board): BoardData {
            return BoardData(
                bid = board.id,
                uid = board.uid,
                title = board.title,
                body = board.body,
                createDate = board.createDate,
                modifiedAt = board.modifiedAt
            )
        }
    }
}

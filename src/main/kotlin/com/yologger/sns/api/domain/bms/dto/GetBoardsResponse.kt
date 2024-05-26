package com.yologger.sns.api.domain.bms.dto

data class GetBoardsResponse(
    val size: Int,
    val boards: List<BoardData>
)

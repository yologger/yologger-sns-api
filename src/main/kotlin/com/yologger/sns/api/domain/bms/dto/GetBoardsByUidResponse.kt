package com.yologger.sns.api.domain.bms.dto

data class GetBoardsByUidResponse(
    val size: Int,
    val boards: List<BoardData>
)

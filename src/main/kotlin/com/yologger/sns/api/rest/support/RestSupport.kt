package com.yologger.sns.api.rest.support

import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <T> Page<T>.wrapPage() = ResponseEntity.ok(
    PageResponse(
        data = this.content,
        page = this.pageable.pageNumber,
        size = this.size,
        totalPages = this.totalPages,
        totalElements = this.totalElements
    )
)

/** Wrap Response Ok */
fun <T> T.wrapOk() = ResponseEntity.ok(Response(this))

fun <T> T.wrapConflict() = ResponseEntity.status(HttpStatus.CONFLICT).body(Response(this))

/** Wrap Response Created */
fun <T> T.wrapCreated() = ResponseEntity.status(HttpStatus.CREATED).body(Response(this))

/** Wrap Response Void */
fun Unit.wrapVoid() = ResponseEntity.noContent()

/** Wrap Response */
fun <T> T.wrap() = ResponseEntity.ok(this)
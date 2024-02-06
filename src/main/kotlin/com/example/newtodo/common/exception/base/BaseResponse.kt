package com.example.newtodo.common.exception.base

import org.springframework.http.HttpStatus

data class BaseResponse(
    val status: HttpStatus,
    val message: String
)
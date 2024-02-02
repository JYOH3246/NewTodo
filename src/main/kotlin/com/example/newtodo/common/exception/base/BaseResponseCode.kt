package com.example.newtodo.common.exception.base

import org.springframework.http.HttpStatus

enum class BaseResponseCode (val status: HttpStatus, val message: String) {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다."),
    INVALID_TODO_CARD(HttpStatus.NOT_FOUND, "할일카드를 찾을 수 없습니다.."),
    INVALID_TODO(HttpStatus.NOT_FOUND, "할일을 찾을 수 없습니다."),
    INVALID_COMMENT(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.");

}
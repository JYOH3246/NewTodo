package com.example.newtodo.common.s3

import java.time.LocalDateTime

data class UploadRequest(
    val userId: Long,
    val uploadDate: LocalDateTime,
)
package com.example.newtodo.common.s3

import java.time.LocalDateTime

data class UploadResponse(
    val userId: Long,
    val fileSize: Int,
    val uploadDate: LocalDateTime,
    val url: String
) {
    companion object {
        fun from(aws: Aws): UploadResponse {
            return UploadResponse(
                userId = aws.userId,
                fileSize = aws.fileSize,
                uploadDate = aws.uploadDate,
                url = aws.url
            )
        }
    }
}
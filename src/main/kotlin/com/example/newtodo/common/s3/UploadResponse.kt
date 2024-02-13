package com.example.newtodo.common.s3

data class UploadResponse(
    val fileSize: Int,
    val url: String
) {
    companion object {
        fun from(aws: Aws): UploadResponse {
            return UploadResponse(
                fileSize = aws.fileSize,
                url = aws.url
            )
        }
    }
}
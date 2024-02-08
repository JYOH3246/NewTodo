package com.example.newtodo.common.s3


import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/AWS")
class AwsController(
    private val awsS3Service: AwsS3Service
) {
    @PostMapping(
        "/upload/{userId}",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun uploadImg(
        @RequestPart file: List<MultipartFile>,
        @PathVariable userId: Long
    ): ResponseEntity<ArrayList<UploadResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(awsS3Service.upload(userId, file))
    }

}
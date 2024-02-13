package com.example.newtodo.common.s3


import com.example.newtodo.common.security.jwt.principal.CustomUserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/AWS")
class AwsController(
    private val awsS3Service: AwsS3Service
) {
    @PostMapping(
        "/upload",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun uploadImg(
        @RequestPart file: List<MultipartFile>,
        @AuthenticationPrincipal customUserPrincipal: CustomUserPrincipal
    ): ResponseEntity<ArrayList<UploadResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(awsS3Service.upload(customUserPrincipal.id, file))
    }

}
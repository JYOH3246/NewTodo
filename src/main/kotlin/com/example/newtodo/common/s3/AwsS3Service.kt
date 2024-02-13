package com.example.newtodo.common.s3


import com.amazonaws.services.s3.model.ObjectMetadata
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream


@Service
class AwsS3Service(
    private val s3Config: S3Config,
    private val awsRepository: AwsRepository
) {
    @Transactional
    fun upload(userId: Long, files: List<MultipartFile>): ArrayList<UploadResponse> {
        val imageUrls = ArrayList<String>()
        for (file in files) {
            val originalFileName: String? = file.originalFilename
            val objectMetadata = ObjectMetadata()
            objectMetadata.contentLength = file.size
            objectMetadata.contentType = file.contentType
            val inputStream: InputStream = file.inputStream
            s3Config.amazonS3Client().putObject(s3Config.bucketName, originalFileName, inputStream, objectMetadata)
            val uploadFileUrl = s3Config.amazonS3Client().getUrl(s3Config.bucketName, originalFileName).toString()
            imageUrls.add(uploadFileUrl)
        }
        val responseList = ArrayList<UploadResponse>()
        for ((index, file) in files.withIndex()) {
            val aws = Aws(
                fileSize = file.size.toInt(),
                url = imageUrls[index]
            )
            responseList.add(aws.let { UploadResponse.from(it) })
            awsRepository.save(aws)
        }

        return responseList
    }
    /*
    @Transactional
    fun storeUrls(userId:Long, imageUrls: List<String>, files: List<MultipartFile>): ArrayList<UploadResponse> {
        val responseList = ArrayList<UploadResponse>()

        for ((index, file) in files.withIndex()) {
            val storeUrlResponse = UploadResponse(
                userId = userId,
                fileSize = file.size.toInt(),
                uploadDate = LocalDateTime.now(),
                url = imageUrls[index]
            )
            responseList.add(storeUrlResponse)
        }

        return responseList


    }

     */
}

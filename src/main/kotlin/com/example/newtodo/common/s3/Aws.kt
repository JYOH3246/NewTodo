package com.example.newtodo.common.s3

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "aws")
class Aws(
    @Column(name = "userId")
    val userId: Long,
    @Column(name = "fileSize")
    var fileSize: Int,
    @Column(name = "uploadDate")
    var uploadDate: LocalDateTime,
    @Column(name = "url")
    var url: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

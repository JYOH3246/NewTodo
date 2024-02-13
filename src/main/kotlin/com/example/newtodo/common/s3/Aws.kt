package com.example.newtodo.common.s3

import com.example.newtodo.common.audit.BaseUserEntity
import jakarta.persistence.*

@Entity
@Table(name = "aws")
class Aws(
    @Column(name = "fileSize")
    var fileSize: Int,
    @Column(name = "url")
    var url: String,
) : BaseUserEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

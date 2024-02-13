package com.example.newtodo.member.entity

import com.example.newtodo.common.audit.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "member")

class Member(
    @Column(name = "email")
    val email: String,
    @Column(name = "password")
    var password: String,
    @Column(name = "nickname")
    var nickname: String,
    @Column(name = "phoneNumber")
    var phoneNumber: String?,
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: MemberRole,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}
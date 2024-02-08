package com.example.newtodo.member.dto

import com.example.newtodo.member.entity.Member

data class MemberInfoResponse(
    val email: String,
    val nickname: String,
    val role: String,
    val phoneNumber: String?,
) {
    companion object {
        fun from(member: Member): MemberInfoResponse {
            return MemberInfoResponse(
                email = member.email,
                nickname = member.nickname,
                role = member.role.name,
                phoneNumber = member.phoneNumber
            )
        }
    }
}

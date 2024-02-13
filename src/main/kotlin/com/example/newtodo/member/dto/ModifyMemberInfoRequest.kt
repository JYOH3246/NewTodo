package com.example.newtodo.member.dto

data class ModifyMemberInfoRequest(
    val nickname: String,
    val password: String,
    var reenter: String,
    val phoneNumber: String,
)

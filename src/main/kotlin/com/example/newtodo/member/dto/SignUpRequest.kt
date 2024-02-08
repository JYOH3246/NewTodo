package com.example.newtodo.member.dto

data class SignUpRequest(
    val email: String,
    val password: String,
    val nickname: String,
    val role: String,
    val phoneNumber: String
)

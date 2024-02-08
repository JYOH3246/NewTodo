package com.example.newtodo.member.service

import com.example.newtodo.member.dto.*


interface MemberService {
    fun signUp(request: SignUpRequest): SignUpResponse
    fun login(request: EmailLoginRequest): LoginResponse
    fun getMyInfo(id: Long): MemberInfoResponse
    fun modifyMyInfo(id: Long, request: ModifyMemberInfoRequest): MemberInfoResponse
}
package com.example.newtodo.member.service

import com.example.newtodo.member.dto.LoginResponse
import com.example.newtodo.member.dto.MemberInfoResponse
import com.example.newtodo.member.dto.ModifyOauthMemberInfoRequest
import org.springframework.security.oauth2.core.user.OAuth2User

interface Oauth2MemberService {
    fun login(oAuth2User: OAuth2User): LoginResponse
    fun modifyMyInfo(id: Long, request: ModifyOauthMemberInfoRequest): MemberInfoResponse
}
package com.example.newtodo.member.controller

import com.example.newtodo.common.security.jwt.principal.CustomUserPrincipal
import com.example.newtodo.member.dto.MemberInfoResponse
import com.example.newtodo.member.dto.ModifyMemberInfoRequest
import com.example.newtodo.member.dto.ModifyOauthMemberInfoRequest
import com.example.newtodo.member.service.MemberService
import com.example.newtodo.member.service.Oauth2MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/members/info")
class MemberInfoController(
    private val memberService: MemberService,
    private val oauth2MemberService: Oauth2MemberService
) {
    @GetMapping
    fun getMyInfo(@AuthenticationPrincipal customUserPrincipal: CustomUserPrincipal): ResponseEntity<MemberInfoResponse> {
        return status(HttpStatus.OK).body(memberService.getMyInfo(customUserPrincipal.id))
    }

    @PatchMapping
    @PreAuthorize("hasAnyRole('MEMBER','ADMIN')")
    fun modifyMyInfo(
        @RequestBody request: ModifyMemberInfoRequest,
        @AuthenticationPrincipal customUserPrincipal: CustomUserPrincipal
    ): ResponseEntity<MemberInfoResponse> {
        return status(HttpStatus.OK).body(memberService.modifyMyInfo(customUserPrincipal.id, request))
    }

    @PatchMapping("/oauth")
    @PreAuthorize("hasRole('OAUTH_MEMBER')")
    fun modifyAuthInfo(
        @RequestBody request: ModifyOauthMemberInfoRequest,
        @AuthenticationPrincipal customUserPrincipal: CustomUserPrincipal
    ): ResponseEntity<MemberInfoResponse> {
        return status(HttpStatus.OK).body(oauth2MemberService.modifyMyInfo(customUserPrincipal.id, request))
    }
}
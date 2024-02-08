package com.example.newtodo.member.controller


import com.example.newtodo.member.dto.EmailLoginRequest
import com.example.newtodo.member.dto.LoginResponse
import com.example.newtodo.member.dto.SignUpRequest
import com.example.newtodo.member.dto.SignUpResponse
import com.example.newtodo.member.service.MemberService
import com.example.newtodo.member.service.Oauth2MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberService: MemberService,
    private val oauth2MemberService: Oauth2MemberService
) {
    //1. 이메일 로그인
    @PostMapping("/login/email")
    fun loginWithEmail(@RequestBody loginRequest: EmailLoginRequest): ResponseEntity<LoginResponse> {
        return status(HttpStatus.OK).body(memberService.login(loginRequest))
    }

    //2. 이메일 회원가입
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<SignUpResponse> {
        return status(HttpStatus.OK).body(memberService.signUp(signUpRequest))
    }

    //3. 구글 로그인
    @PostMapping("/login/google")
    fun loginWithGoogle(@AuthenticationPrincipal oAuth2User: OAuth2User): ResponseEntity<LoginResponse> {
        return status(HttpStatus.OK).body(oauth2MemberService.login(oAuth2User))

    }

}
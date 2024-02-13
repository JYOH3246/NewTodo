package com.example.newtodo.member.service

import com.example.newtodo.common.security.jwt.JwtPlugin
import com.example.newtodo.member.dto.*
import com.example.newtodo.member.entity.Member
import com.example.newtodo.member.entity.MemberRole
import com.example.newtodo.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin,
) : MemberService, Oauth2MemberService {
    @Transactional
    override fun signUp(request: SignUpRequest): SignUpResponse {
        val member = Member(
            email = request.email,
            password = passwordEncoder.encode((request.password)),
            nickname = request.nickname,
            role = when (request.role) {
                "ADMIN" -> MemberRole.ADMIN
                "MEMBER" -> MemberRole.MEMBER
                else -> throw IllegalArgumentException("올바르지 않은 역할입니다.")
            },
            phoneNumber = request.phoneNumber,
        )
        memberRepository.save(member)
        return SignUpResponse()
    }

    @Transactional
    override fun login(request: EmailLoginRequest): LoginResponse {
        val member = memberRepository.findByEmail(request.email)
            ?: throw IllegalArgumentException("존재하지 않는 이메일입니다.")
        if (!passwordEncoder.matches(request.password, member.password)) {
            throw IllegalArgumentException("비밀번호가 일치하지 않습니다.")
        }
        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = member.id.toString(),
                email = member.email,
                role = member.role.name

            )
        )
    }

    override fun getMyInfo(id: Long): MemberInfoResponse {
        val member = memberRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("존재하지 않는 회원입니다.")
        return member.let { MemberInfoResponse.from(it) }
    }

    @Transactional
    override fun modifyMyInfo(id: Long, request: ModifyMemberInfoRequest): MemberInfoResponse {
        val member = memberRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("존재하지 않는 회원입니다.")
        if (passwordEncoder.matches(request.password, member.password)) {
            if (request.password == request.reenter) {
                member.nickname = request.nickname
                member.password = passwordEncoder.encode(request.password)
                member.phoneNumber = request.phoneNumber
                return member.let { MemberInfoResponse.from(it) }
            } else throw IllegalArgumentException("재입력된 비밀번호가 일치하지 않습니다.")

        } else throw IllegalArgumentException("잘못된 비밀번호가 입력되었습니다.")
    }

    @Transactional
    override fun login(oAuth2User: OAuth2User): LoginResponse {
        if (!memberRepository.existsByEmail(oAuth2User.attributes["email"] as String)) {
            val member = Member(
                email = oAuth2User.attributes["email"] as String,
                password = passwordEncoder.encode(("1111111")),
                nickname = "임시닉네임123",
                phoneNumber = null,
                role = MemberRole.OAUTH_MEMBER,
            )
            memberRepository.save(member)
        }
        val member = memberRepository.findByEmail(oAuth2User.attributes["email"] as String)
        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = member!!.id.toString(),
                email = member.email,
                role = member.role.name

            )
        )
    }

    @Transactional
    override fun modifyMyInfo(id: Long, request: ModifyOauthMemberInfoRequest): MemberInfoResponse {
        val member = memberRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("존재하지 않는 회원입니다.")
        member.nickname = request.nickname
        member.phoneNumber = request.phoneNumber
        return member.let { MemberInfoResponse.from(it) }
    }
}
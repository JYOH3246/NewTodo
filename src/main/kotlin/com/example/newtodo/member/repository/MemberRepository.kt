package com.example.newtodo.member.repository

import com.example.newtodo.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?
    fun existsByEmail(email: String): Boolean
}
package com.example.newtodo.common.security.jwt.principal

import org.springframework.security.core.GrantedAuthority

abstract class CustomUserPrincipal(
    open val id: Long,
    open val email: String,
    open val authorities: Collection<GrantedAuthority>
)
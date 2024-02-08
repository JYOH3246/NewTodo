package com.example.newtodo.common.security.jwt.principal

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class Oauth2UserPrincipal(
    override val id: Long,
    override val email: String,
    override val authorities: Collection<GrantedAuthority>
) : CustomUserPrincipal(id, email, authorities) {
    constructor(id: Long, email: String, roles: Set<String>) : this(
        id,
        email,
        roles.map { SimpleGrantedAuthority("ROLE_$it") })

}
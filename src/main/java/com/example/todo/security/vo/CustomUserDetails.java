package com.example.todo.security.vo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L; // 직렬화 버전 UID

    private final Long userId;
    private final String username;
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities; // 사용자 권한 목록

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getUserId() {
        return userId;
    }
}

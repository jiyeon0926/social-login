package com.example.todo.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationEventListeners {

    // 모든 인증 이벤트의 공통 처리
    @EventListener
    public void handleAuthenticationEvent(AbstractAuthenticationEvent event) {
        log.info("[handleAuthenticationEvent] " + event);
    }
    
    // 잘못된 자격 증명 이벤트 처리
    @EventListener
    public void handleBadCredentials(AuthenticationFailureBadCredentialsEvent event) {
        log.info("handleBadCredentials");
    }

    // 로그인 성공 이벤트 처리
    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        log.info("handleAuthenticationSuccess");
    }
}

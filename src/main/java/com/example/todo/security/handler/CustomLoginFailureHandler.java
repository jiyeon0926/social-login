package com.example.todo.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;

@Slf4j
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    // 인증 요청이 저장된 캐시에서 이전 요청을 가져오기 위한 객체
    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.info("[onAuthenticationFailure exception] " + exception);

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        // 로그인 실패 후에도 원래 요청한 URL로 리다이랙트
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();

            log.info("Login Failure targetUrl = " + targetUrl);

            response.sendRedirect(targetUrl);
        }
    }
}

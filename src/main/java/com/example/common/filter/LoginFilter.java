package com.example.common.filter;

import com.example.common.consts.Const;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter{
    private static final String[] WHITE_LIST = {"/", "/signup", "/login", "/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestURI = httpServletRequest.getRequestURI();

        // 화이트 리스트에 포함되어 있지 않은 경우, 로그인 여부 확인
        if(isWhiteList(requestURI)){
            HttpSession session = httpServletRequest.getSession(false);
            if(session != null || session.getAttribute(Const.LOGIN_MEMBER) == null){
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 하세요.");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    // 화이트 리스트에 포함되어있다면 true
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
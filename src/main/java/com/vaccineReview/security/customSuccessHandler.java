package com.vaccineReview.security;

import com.vaccineReview.login.vo.loginVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class customSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();

        loginVO loginVO = (loginVO) authentication.getPrincipal();

        session.setAttribute("userName",loginVO.getUserLastName());                    //유저명
        session.setAttribute("userMail",authentication.getName());                     //유저메일
        session.setAttribute("userPicture","/bootstrap/img/undraw_profile.svg");    //회원가입시 사진 없으므로 기본 path

        response.sendRedirect("/");
    }
}

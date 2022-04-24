package com.vaccineReview.login.controller;

import com.vaccineReview.login.service.loginService;
import com.vaccineReview.login.vo.loginVO;
import com.vaccineReview.security.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 소셜로그인 Controller
 * @author kh
 * @since 2022.04.12
 * @version 1.0
 *
 * << 개정이력(Modification Information) >>
 *
 *  수정일         수정자           수정내용
 *  ----------    -------------   -------------------
 *  2022.04.12    남기현           최초 생성
 *
 */

@Controller
@RequiredArgsConstructor
public class loginController {

    private final loginService service;

    private final HttpSession httpSession;

    //로그인 뷰
    @GetMapping("/layout/login")
    public String login() {
        return "layout/login";
    }



    //회원가입 뷰
    @GetMapping("/login/register")
    public String register() {
        return "layout/register";
    }
    
    //회원가입 로직
    @PostMapping("/login/register")
    public String registerUser(loginVO loginVO) {
        service.joinUser(loginVO);
        return "redirect:/layout/login";
    }

}

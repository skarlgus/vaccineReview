package com.vaccineReview.login.controller;

import com.vaccineReview.login.service.loginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(String code) {

        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        //service.kakaoLogin(code);

        return "redirect:/";
    }

    @GetMapping("/layout/login")
    public String login() {
        return "layout/login";
    }
}

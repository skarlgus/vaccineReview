package com.vaccineReview.login.controller;

import com.vaccineReview.login.service.loginService;
import com.vaccineReview.login.vo.loginVO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

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
    public String registerUser(loginVO loginVO,Model model) {
        //사용중인 이메일인지 체크
        int count = service.checkUser(loginVO);

        //중복 email 체크
        if (count>0){
            model.addAttribute("emailInfo","이미 회원가입된 email입니다.");  //중복 메일 msg
            loginVO.setUserId("");                                                               //메일 초기화
            model.addAttribute("loginVO",loginVO);                                   //loginVO
            return "/layout/register";

        //회원가입 진행
        }else{
            //비밀번호 체크
            if(loginVO.getUserPw().equals(loginVO.getUserPwChk())){
                service.joinUser(loginVO);
                return "redirect:/layout/login";
            }else{
                model.addAttribute("pwInfo","비밀번호가 일치하지 않습니다.");  //비밀번호 불일치 msg
                loginVO.setUserPw("");                                                           //비밀번호 초기화
                loginVO.setUserPwChk("");                                                        //비밀번호 초기화
                model.addAttribute("loginVO",loginVO);                               //loginVO



                return "/layout/register";
            }

        }

    }

}

package com.vaccineReview.test.controller;

import com.vaccineReview.domain.User;
import com.vaccineReview.domain.UserRepository;
import com.vaccineReview.test.service.testService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class testController {
    private final testService service;
    private final UserRepository userRepository;

    @GetMapping("/test")
    public void test(Model model) {
        //젠킨스 weebhook테스트
        
        //슬랙 연동 테스트
    }


}

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
        Map<String,Object> map = service.test();
        System.out.println("@@@@@@#######$$$$$$$$$$$$$"+map.get("TEST"));
        System.out.println("map = " + map);
        System.out.println("map = " + map.size());

        List<User> user =  userRepository.findAllByEmail("rlgus0419@gmail.com");
        System.out.println("user.size() = " + user.size());




    }


}

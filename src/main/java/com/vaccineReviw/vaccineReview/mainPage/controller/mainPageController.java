package com.vaccineReviw.vaccineReview.mainPage.controller;

import com.vaccineReviw.vaccineReview.mainPage.service.mainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class mainPageController {

    private final mainPageService service;

    @GetMapping("/")
    public String main() {

        System.out.println("Controller");
        service.corona();

        return "index";
    };

}

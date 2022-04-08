package com.vaccineReviw.vaccineReview.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class login {

    @GetMapping("/layout/login.html")
    public String main() {

        return "/layout/login";
    }
}

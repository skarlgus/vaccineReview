package com.vaccineReviw.vaccineReview.mainPage.service;

import com.vaccineReviw.vaccineReview.mainPage.mapper.mainPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class mainPageService {

    private final mainPageMapper mapper;

    public void corona() {
        System.out.println("service");
        mapper.corona();
    }
}

package com.vaccineReview.test.service;

import com.vaccineReview.test.mapper.testMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class testService {

    private final testMapper mapper;

    public Map<String, Object> test() {
        return mapper.test();
    }
}

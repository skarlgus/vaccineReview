package com.vaccineReview.login.service;

import com.vaccineReview.login.mapper.loginMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 소셜로그인 Service
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

@Service
@RequiredArgsConstructor
public class loginService {

    private final loginMapper mapper;

    public Map<String,Object> getUserInfo(int i) {
        return mapper.getUserInfo(i);
    }
}

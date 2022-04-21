package com.vaccineReview.login.mapper;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class loginMapper {

    private final SqlSessionTemplate sqlSessionTemplate;

    public Map<String, Object> getUserInfo(int i) {
        return sqlSessionTemplate.selectOne("userMapper.getUserInfo");
    }
}

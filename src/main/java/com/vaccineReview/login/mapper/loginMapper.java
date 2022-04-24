package com.vaccineReview.login.mapper;

import com.vaccineReview.login.vo.loginVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
public interface loginMapper {

    //로그인
    loginVO getUserAccount(String userID);

    //회원가입
    void saveUser(loginVO loginVO);
}

package com.vaccineReview.login.mapper;

import com.vaccineReview.login.vo.loginVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
public interface loginMapper {

    //로그인
    loginVO getUserAccount(String userID);

    //회원가입 중복 체크
    int checkUser(loginVO loginVO);

    //회원가입
    void saveUser(loginVO loginVO);

    //이메일 회원가입 체크
    int registerEmailChk(String email);
    
    //비밀번호 찾기 비번 업데이트
    void updatePw(String email, String encodePw);
}

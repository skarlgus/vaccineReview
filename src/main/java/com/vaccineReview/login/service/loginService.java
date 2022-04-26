package com.vaccineReview.login.service;

import com.vaccineReview.login.mapper.loginMapper;
import com.vaccineReview.login.vo.loginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class loginService implements UserDetailsService {

    SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:sss");
    Date time = new Date();
    String localTime = format.format(time);

    private final loginMapper mapper;

    /**************************************************
     *   note : 회원가입 체크
     * ************************************************/
    public int checkUser(loginVO loginVO) {


        return mapper.checkUser(loginVO);
    }

    /**************************************************
     *   note : 회원가입
     * ************************************************/
    @Transactional
    //트랜잭션 보장이 된 메소드로 설정 해준다.
    public void joinUser(loginVO loginVO){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        loginVO.setUserPw(passwordEncoder.encode(loginVO.getPassword()));
        loginVO.setUserAuth("회원가입");
        loginVO.setAppendDate(localTime);
        loginVO.setUpdateDate(localTime);

        mapper.saveUser(loginVO);
    }

    /**************************************************
     *   note : 로그인 인증
     * ************************************************/
    @Override
    public loginVO loadUserByUsername(String userId) throws UsernameNotFoundException {
        //여기서 받은 유저 패스워드와 비교하여 로그인 인증
        loginVO loginVO = mapper.getUserAccount(userId);
        if (loginVO == null){
            throw new UsernameNotFoundException("User not authorized.");
        }
        return loginVO;
    }

}

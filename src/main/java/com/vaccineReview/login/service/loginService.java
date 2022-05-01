package com.vaccineReview.login.service;

import com.vaccineReview.login.mapper.loginMapper;
import com.vaccineReview.login.vo.loginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.Context;
import org.thymeleaf.expression.Strings;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.standard.expression.MessageExpression;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

//import org.apache.commons.lang3.RandomStringUtils;
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

    private final JavaMailSender javaMailSender;

    private final SpringTemplateEngine templateEngine;

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

    /**************************************************
     *   note : 비밀번호 찾기(메일)
     * ************************************************/
    public void forgotPassWord(String email) throws IOException, MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        //10자리 랜덤 문자열
        //배포시 pom오류로 인해 아래 방법으로 대체
        /*String generatedString = RandomStringUtils.randomAlphanumeric(10);*/

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        //메일 제목 설정
        helper.setSubject("VaccineReview 비밀번호 변경 안내");
        //수신자 설정
        helper.setTo(email);
        //템플릿에 전달할 데이터 설정
        Context context = new Context();
        context.setVariable("pw", generatedString);
        //메일 내용 설정 : 템플릿 프로세스
        String html = templateEngine.process("mail",context);
        helper.setText(html, true);

        //메일 보내기
        javaMailSender.send(message);

        //비밀번호 업데이트
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePw = passwordEncoder.encode(generatedString);
        mapper.updatePw(email,encodePw);
    }

    /**************************************************
     *   note : 회원가입된 이메일인지 체크
     * ************************************************/
    public int registerEmailChk(String email) {
        return mapper.registerEmailChk(email);
    }
}

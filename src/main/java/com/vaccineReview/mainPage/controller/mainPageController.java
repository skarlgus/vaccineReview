package com.vaccineReview.mainPage.controller;

import com.sun.mail.imap.protocol.MODSEQ;
import com.vaccineReview.mainPage.service.mainPageService;
import com.vaccineReview.mainPage.vo.mainPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 대시보드 Controller
 * @author kh
 * @since 2022.04.11
 * @version 1.0
 *
 * << 개정이력(Modification Information) >>
 *
 *  수정일         수정자           수정내용
 *  ----------    -------------   -------------------
 *  2022.04.11    남기현           최초 생성
 *
 */

@Controller
@RequiredArgsConstructor
public class mainPageController {

    private final mainPageService service;

    private final HttpSession httpSession;

    @GetMapping("/")
    public String main(Model model) throws IOException {

        //결제 여부 확인
        String userMail = (String) httpSession.getAttribute("userMail");

        int chkImport = service.searchImport(userMail);

        model.addAttribute("chkImport",chkImport);

        DecimalFormat decFormat = new DecimalFormat("###,###");

        //백신 후기 갯수
        mainPageVO mainPageVO = service.getReviewBoard();
        model.addAttribute("boardCount",mainPageVO.getBoardCount());  //금일 후기 갯수

        //코로나19 감염 현황 API
        HashMap<String, Integer> coronaNowMap = service.coronaNow();
        model.addAttribute("decideCnt",decFormat.format(coronaNowMap.get("decideCnt"))+"명");  //누적확진자 수
        model.addAttribute("deathCnt",decFormat.format(coronaNowMap.get("deathCnt"))+"명");    //누적사망자 수

        //코로나19 연령별·성별감염 감염 현황 현황 API
        List<HashMap<String, Object>> coronaVaccineMap = service.coronaVaccine();
        HashMap map = (HashMap<String, Object>) coronaVaccineMap.get(0);
        model.addAttribute("A", map.get("0-9"));
        model.addAttribute("B", map.get("10-19"));
        model.addAttribute("C", map.get("20-29"));
        model.addAttribute("D", map.get("30-39"));
        model.addAttribute("E", map.get("40-49"));
        model.addAttribute("F", map.get("50-59"));
        model.addAttribute("G", map.get("60-69"));
        model.addAttribute("H", map.get("70-79"));
        model.addAttribute("I", map.get("80 이상"));

        /*
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            model.addAttribute(key, map.get(key));  //연령대별 확진률 ex) ("0-10",12.11)
        }
        */

        //코로나19 백신 누적 API
        HashMap<String, Integer> coronaVaccineNowMap = service.coronaVaccineNow();
        model.addAttribute("thirdCntRate",coronaVaccineNowMap.get("thirdCntRate"));  //3차 접종률

        //세션 값
        model.addAttribute("userName",httpSession.getAttribute("userName"));  //사용자명
        model.addAttribute("userMail",httpSession.getAttribute("userMail"));  //사용자메일
        model.addAttribute("userPicture",httpSession.getAttribute("userPicture"));  //사용자프로필

        return "index";
    };

    @PostMapping("/mainPage/imPORT")
    public String imPORT(@RequestParam Map<String, Object> param) {

        param.put("userMail",httpSession.getAttribute("userMail"));
        service.saveImport(param);

        return "redirect:/index";
    }

}

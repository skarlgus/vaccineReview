package com.vaccineReviw.vaccineReview.mainPage.controller;

import com.vaccineReviw.vaccineReview.mainPage.service.mainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

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

    @GetMapping("/")
    public String main(Model model) throws IOException {

        //코로나19 감염 현황 API
        HashMap<String, Integer> coronaNowMap = service.coronaNow();
        model.addAttribute("decideCnt",coronaNowMap.get("decideCnt"));  //누적확진자 수
        model.addAttribute("deathCnt",coronaNowMap.get("deathCnt"));    //누적사망자 수

        //코로나19 연령별·성별감염 감염 현황 현황 API
        List<HashMap<String, Object>> coronaBaccineMap = service.coronaVaccine();
        HashMap map = (HashMap<String, Object>)coronaBaccineMap.get(0);
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            model.addAttribute(key,map.get(key));  //연령대별 확진률 ex) ("0-10",12.11)
        }


        
        return "index";
    };

}

package com.vaccineReview.mainPage.mapper;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 대시보드 Mapper
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

@Repository
public class mainPageMapper {


    public Map<String, Object> getReviewBoard(int i) {
        return null;
    }
}

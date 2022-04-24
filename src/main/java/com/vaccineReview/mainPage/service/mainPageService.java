package com.vaccineReview.mainPage.service;

import com.vaccineReview.mainPage.mapper.mainPageMapper;
import com.vaccineReview.mainPage.vo.mainPageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 대시보드 Service
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

@Service
@RequiredArgsConstructor
@Slf4j
public class mainPageService {

    /**************************************************
     *   note : mainPage MAPPER class inject.
     * ************************************************/
    private final mainPageMapper mapper;

    /**************************************************
     *   note : 금일 후기 작성갯수
     * ************************************************/
    public mainPageVO getReviewBoard() {
        return mapper.getReviewBoard();
    }

    /**************************************************
     *   note : 코로나19 감염 현황 API
     * ************************************************/
    public HashMap<String, Integer> coronaNow() throws IOException{

        /*오늘 날짜 구하기*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date now = new Date();

        String nowTime = sdf.format(now);

        //return map
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        /*코로나19 감염 현황 API 호출*/
        try{

            StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=IfLCgeoScc4aMugyZmYPSGCObIwFZjT%2F8p8ogPAyUe2avXdUgG9rCZE5E9cufGnGob0mtU1TKZubx1HgMJxpGA%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(nowTime, "UTF-8")); /*검색할 생성일 범위의 시작*/
            urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(nowTime, "UTF-8")); /*검색할 생성일 범위의 종료*/

            URL url = new URL(urlBuilder.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.setRequestProperty("Content-type", "application/json");

            log.info("코로나19 감염 현황 API Response code: " + conn.getResponseCode());

            BufferedReader rd;

            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();

            String line;

            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            rd.close();

            conn.disconnect();
            /*코로나19 감염 현황 API 종료*/

            /*xml data json 가공*/
            JSONObject jObject = XML.toJSONObject(sb.toString());

            String text = jObject.toString(4);  //xml data 확인

            JSONObject jResponse = jObject.getJSONObject("response");

            JSONObject jBody = jResponse.getJSONObject("body");

            JSONObject jItems = jBody.getJSONObject("items");

            JSONObject jItem = jItems.getJSONObject("item");

            int decideCnt = (int) jItem.get("decideCnt");   //누적확진자수
            int deathCnt = (int) jItem.get("deathCnt");     //누적사망자수

            map.put("decideCnt",decideCnt);
            map.put("deathCnt",deathCnt);

        } catch (Exception e) {
            log.info("[ERROR] 코로나19 감염 현황 API 호출 error");
            map.put("decideCnt",0);
            map.put("deathCnt",0);
        }
        /*xml data json 가공종료*/

        return map;
    }

    /**************************************************
     *   note : 코로나19 연령별·성별감염 감염 현황 API
     * ************************************************/
    public List<HashMap<String, Object>> coronaVaccine() throws IOException {

        /*오늘 날짜 구하기*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date now = new Date();

        String nowTime = sdf.format(now);

        //return listMap
        List<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();

        //retrun listMap에 추가될 map
        HashMap<String, Object> map = new HashMap<String, Object>();

        /*코로나19 연령별·성별감염 감염 현황 API 호출*/
        try{
            StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19GenAgeCaseInfJson"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=IfLCgeoScc4aMugyZmYPSGCObIwFZjT%2F8p8ogPAyUe2avXdUgG9rCZE5E9cufGnGob0mtU1TKZubx1HgMJxpGA%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(nowTime, "UTF-8")); /*검색할 생성일 범위의 시작*/
            urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(nowTime, "UTF-8")); /*검색할 생성일 범위의 종료*/

            URL url = new URL(urlBuilder.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.setRequestProperty("Content-type", "application/json");

            log.info("코로나19 연령별·성별감염 감염 현황 API Response code: " + conn.getResponseCode());

            BufferedReader rd;

            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();

            String line;

            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            rd.close();

            conn.disconnect();
            /*코로나19 연령별·성별감염 감염 현황 API 종료*/

            /*xml data json 가공*/
            JSONObject jObject = XML.toJSONObject(sb.toString());

            String text = jObject.toString(4);  //xml data 확인

            JSONObject jResponse = jObject.getJSONObject("response");

            JSONObject jBody = jResponse.getJSONObject("body");

            JSONObject jItems = jBody.getJSONObject("items");

            JSONArray jArray = jItems.getJSONArray("item");

            if(jArray.length() > 0){
                for(int i=0; i<jArray.length(); i++){
                    JSONObject jsonObj = jArray.getJSONObject(i);
                    Object confCaseRate = jsonObj.get("confCaseRate");   //확진률
                    String gubun = (String) jsonObj.get("gubun");        //나이

                    map.put(gubun,confCaseRate);
                    listMap.add(map);
                }
            }

        } catch (Exception e) {
            log.info("[ERROR] 코로나19 백신 현황 API 호출 error");
            map.put("0","0");
            listMap.add(map);
        }
        /*xml data json 가공종료*/

        return listMap;
    }

    /**************************************************
     *   note : 코로나19 백신 현황 API
     * ************************************************/
    public HashMap<String, Integer> coronaVaccineNow() {

        //retrun map
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        /*코로나19 백신 현황 API 호출*/
        try{

            StringBuilder urlBuilder = new StringBuilder("https://nip.kdca.go.kr/irgd/cov19stats.do"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("list","UTF-8") + "all");

            URL url = new URL(urlBuilder.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.setRequestProperty("Content-type", "application/json");

            log.info("코로나19 백신 현황 API Response code: " + conn.getResponseCode());

            BufferedReader rd;

            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();

            String line;

            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            rd.close();

            conn.disconnect();
            /*코로나19 백신 현황 API 종료*/

            /*xml data json 가공*/
            JSONObject jObject = XML.toJSONObject(sb.toString());

            String text = jObject.toString(4);  //xml data 확인

            JSONObject jResponse = jObject.getJSONObject("response");

            JSONObject jBody = jResponse.getJSONObject("body");

            JSONObject jItems = jBody.getJSONObject("items");

            JSONArray jArray = jItems.getJSONArray("item");

            if(jArray.length() > 0){
                JSONObject jsonObj = jArray.getJSONObject(2);

                int thirdCnt = (int) jsonObj.get("thirdCnt");  //3차 접종자 수

                double totalKorean = 51780000;   //대한민국 인구 수
                
                double thirdCntR = (double) 100 / totalKorean * thirdCnt;   //3차 접종률

                int thirdCntRate = (int) Math.round(thirdCntR);   //반올림

                map.put("thirdCntRate",thirdCntRate);

            }

        } catch (Exception e) {
            log.info("[ERROR] 코로나19 백신 현황 API 호출 error");
            map.put("thirdCntRate",0);
        }
        /*xml data json 가공종료*/

        return map;
    }

}

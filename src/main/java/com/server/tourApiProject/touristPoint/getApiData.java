package com.server.tourApiProject.touristPoint;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//서버가 초기화된후 바로 실행되는 코드입니다
@Component
public class getApiData implements org.springframework.boot.ApplicationRunner {

    @Autowired
    private TouristPointController touristPointController;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //지역
        JSONArray area_list = getJson("/areaCode", "");
        List<AreaParams> areaParams = new ArrayList();
        for (Object o : area_list) {
            JSONObject item = (JSONObject) o;
            Long code = (Long) item.get("code");
            String name = (String) item.get("name");

            AreaParams areaParam = new AreaParams(code, name);
            areaParams.add(areaParam);
        }
        touristPointController.createArea(areaParams);

        //시군구
        List<Long> allAreaCode = touristPointController.getAllAreaCode();
        for(Long areaCode: allAreaCode){
            String url2 = "&areaCode=" + areaCode;
            System.out.println(areaCode);

            JSONArray sigungu_list = getJson("/areaCode", url2);
            List<SigunguParams> sigunguParams = new ArrayList();
            for (Object o : sigungu_list) {
                JSONObject item = (JSONObject) o;
                Long code = (Long) item.get("code");
                String name = (String) item.get("name");

                SigunguParams sigunguParam = new SigunguParams(code, name);
                sigunguParams.add(sigunguParam);
            }
            touristPointController.createSigungu(areaCode, sigunguParams);
        }




    };


    //open api 호출해서 결과 리턴하는 함수
    public JSONArray getJson(String url1, String url2){

        String key = "?ServiceKey=BdxNGWQJQFutFYE6DkjePTmerMbwG2fzioTf6sr69ecOAdLGMH4iiukF8Ex93YotSgkDOHe1VxKNOr8USSN6EQ%3D%3D"; //인증키
        String result = "";

        try{
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService" + url1 + key + url2 + "&MobileOS=AND&MobileApp=tourApiProject&_type=json");
            BufferedReader bf; //빠른 속도로 데이터를 처리하기 위해
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine(); //api로 받아온 결과

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject response = (JSONObject)jsonObject.get("response");
            JSONObject body = (JSONObject)response.get("body");
            JSONObject items = (JSONObject)body.get("items");
            Long count = (Long)body.get("totalCount");
            if (count == 1){
                JSONObject item = (JSONObject)items.get("item");
                bf.close();
                //return item;
            }else {
                JSONArray item_list = (JSONArray) items.get("item");
                return item_list;
            }
            System.out.println("api 불러오기 성공");
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("api 불러오기 실패");
        return null;
    }
}
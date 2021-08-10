package com.server.tourApiProject.observation;

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

@Component
public class getApiData implements org.springframework.boot.ApplicationRunner {

    @Autowired
    private ObservationController observationController;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        JSONArray item_list = getJson("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?ServiceKey=",
                "&MobileOS=AND&MobileApp=tourApiProject&_type=json");
        List<AreaParams> areaParams = new ArrayList();
        for (Object o : item_list) {
            JSONObject item = (JSONObject) o;
            Long code = (Long) item.get("code");
            String name = (String) item.get("name");

            AreaParams areaParam = new AreaParams(code, name);
            areaParams.add(areaParam);
        }
        observationController.createArea(areaParams);

//        String key = "BdxNGWQJQFutFYE6DkjePTmerMbwG2fzioTf6sr69ecOAdLGMH4iiukF8Ex93YotSgkDOHe1VxKNOr8USSN6EQ%3D%3D"; //인증키
//        String result = "";
//        List<AreaParams> areaParams = new ArrayList();
//
//        try{
//            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?ServiceKey=" + key +
//                    "&MobileOS=AND&MobileApp=tourApiProject&_type=json");
//
//            BufferedReader bf; //빠른 속도로 데이터를 처리하기 위해
//            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
//            result = bf.readLine(); //api로 받아온 결과
//            //System.out.println("result = " + result);
//
//            JSONParser jsonParser = new JSONParser();
//            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
//            JSONObject response = (JSONObject)jsonObject.get("response");
//            JSONObject body = (JSONObject)response.get("body");
//            JSONObject items = (JSONObject)body.get("items");
//            JSONArray item_list = (JSONArray)items.get("item");
//
//            //System.out.println("body = " + body);
//            //System.out.println("items = " + items);
//            System.out.println("결과 개수 : " + body.get("numOfRows"));
//
//            for (Object o : item_list) {
//                JSONObject item = (JSONObject) o;
//                Long code = (Long) item.get("code");
//                String name = (String) item.get("name");
//
//                AreaParams areaParam = new AreaParams(code, name);
//                areaParams.add(areaParam);
//            }
//            bf.close();
//            observationController.createArea(areaParams);
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    };
    
    public JSONArray getJson(String url1, String url2){

        String key = "BdxNGWQJQFutFYE6DkjePTmerMbwG2fzioTf6sr69ecOAdLGMH4iiukF8Ex93YotSgkDOHe1VxKNOr8USSN6EQ%3D%3D"; //인증키
        String result = "";

        try{
            URL url = new URL(url1 + key + url2);
            BufferedReader bf; //빠른 속도로 데이터를 처리하기 위해
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine(); //api로 받아온 결과

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject response = (JSONObject)jsonObject.get("response");
            JSONObject body = (JSONObject)response.get("body");
            JSONObject items = (JSONObject)body.get("items");
            JSONArray item_list = (JSONArray)items.get("item");
            bf.close();

            System.out.println("api 불러오기 성공");
            return item_list;

        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("api 불러오기 실패");
        return null;
    }
}
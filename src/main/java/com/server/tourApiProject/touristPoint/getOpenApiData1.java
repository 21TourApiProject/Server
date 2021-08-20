package com.server.tourApiProject.touristPoint;

import com.server.tourApiProject.touristPoint.area.AreaController;
import com.server.tourApiProject.touristPoint.area.AreaParams;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeController;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeParams;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Order(1)
@Component
public class getOpenApiData1 implements org.springframework.boot.ApplicationRunner {

    @Autowired
    private AreaController areaController;
    @Autowired
    private ContentTypeController contentTypeController;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //지역
//        JSONArray area_list = getJson("/areaCode", "");
//        for (Object o1 : area_list) {
//            JSONObject item1 = (JSONObject) o1;
//            Long code1 = (Long) item1.get("code");
//            String name1 = (String) item1.get("name");
//
//            JSONArray sigungu_list = getJson("/areaCode", "&areaCode=" + code1);
//            for (Object o2 : sigungu_list) {
//                JSONObject item2 = (JSONObject) o2;
//                Long code2 = (Long) item2.get("code");
//                String name2 = (String) item2.get("name");
//
//                AreaParams areaParams = new AreaParams(code1, name1, code2, name2);
//                areaController.createArea(areaParams);
//            }
//        }


        //서비스 분류 - 관광지
        JSONArray cat1_list1 = getJson("/categoryCode", "&contentTypeId=12");
        for (Object o1 : cat1_list1) {
            JSONObject item1 = (JSONObject) o1;
            String code1 = (String) item1.get("code");
            String name1 = (String) item1.get("name");

            JSONArray cat2_list1 = getJson("/categoryCode", "&cat1=" + code1 + "&contentTypeId=12");
            for (Object o2 : cat2_list1) {
                JSONObject item2 = (JSONObject) o2;
                String code2 = (String) item2.get("code");
                String name2 = (String) item2.get("name");

                JSONArray cat3_list1 = getJson("/categoryCode", "&cat1=" + code1 + "&cat2=" + code2 + "&contentTypeId=12");
                for (Object o3 : cat3_list1) {
                    JSONObject item3 = (JSONObject) o3;
                    String code3 = (String) item3.get("code");
                    String name3 = (String) item3.get("name");

                    ContentTypeParams contentTypeParams = new ContentTypeParams(code1, name1, code2, name2, code3, name3);
                    contentTypeController.createContentType1(contentTypeParams);
                }
            }
        }

        //서비스 분류 - 음식
//        JSONArray cat1_list2 = getJson("/categoryCode", "&contentTypeId=39");
//        for (Object o1 : cat1_list2) {
//            JSONObject item1 = (JSONObject) o1;
//            String code1 = (String) item1.get("code");
//            String name1 = (String) item1.get("name");
//
//            JSONArray cat2_list2 = getJson("/categoryCode", "&cat1=" + code1 + "&contentTypeId=39");
//            for (Object o2 : cat2_list2) {
//                JSONObject item2 = (JSONObject) o2;
//                String code2 = (String) item2.get("code");
//                String name2 = (String) item2.get("name");
//
//                JSONArray cat3_list2 = getJson("/categoryCode", "&cat1=" + code1 + "&cat2=" + code2 + "&contentTypeId=39");
//                for (Object o3 : cat3_list2) {
//                    JSONObject item3 = (JSONObject) o3;
//                    String code3 = (String) item3.get("code");
//                    String name3 = (String) item3.get("name");
//
//                    ContentTypeParams contentTypeParams = new ContentTypeParams(code1, name1, code2, name2, code3, name3);
//                    contentTypeController.createContentType2(contentTypeParams);
//                }
//            }
//        }
    }

    //open api 호출해서 결과 리턴하는 함수
    public JSONArray getJson(String part1, String part2){

        String key = "?ServiceKey=VQ0keALnEea3BkQdEGgwgCD8XNDNR%2Fg98L9D4GzWryl4UYHnGfUUUI%2BHDA6DdzYjjzJmuHT1UmuJZ7wJHoGfuA%3D%3D"; //인증키
        String result = "";

        try{
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService" + part1 + key + part2 + "&MobileOS=AND&MobileApp=tourApiProject&_type=json");
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
                JSONArray item_list = new JSONArray();
                item_list.add(item);
                return item_list;
            }
            else if (count > 10){
                try{
                    URL url2 = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService" + part1 + key + part2 +"&MobileOS=AND&MobileApp=tourApiProject&_type=json&numOfRows="+ count);
                    BufferedReader bf2; //빠른 속도로 데이터를 처리하기 위해
                    bf2 = new BufferedReader(new InputStreamReader(url2.openStream(), "UTF-8"));
                    result = bf2.readLine(); //api로 받아온 결과

                    JSONParser jsonParser2 = new JSONParser();
                    JSONObject jsonObject2 = (JSONObject)jsonParser2.parse(result);
                    JSONObject response2 = (JSONObject)jsonObject2.get("response");
                    JSONObject body2 = (JSONObject)response2.get("body");
                    JSONObject items2 = (JSONObject)body2.get("items");
                    JSONArray item_list2 = (JSONArray) items2.get("item");
                    bf2.close();
                    return item_list2;

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            else {
                JSONArray item_list = (JSONArray) items.get("item");
                bf.close();
                return item_list;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}

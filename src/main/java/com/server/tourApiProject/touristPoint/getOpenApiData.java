package com.server.tourApiProject.touristPoint;

import com.server.tourApiProject.touristPoint.area.AreaController;
import com.server.tourApiProject.touristPoint.area.AreaParams;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeController;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeParams;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeParams2;
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

//서버가 초기화된후 바로 실행되는 코드
@Component
public class getOpenApiData implements org.springframework.boot.ApplicationRunner {

    @Autowired
    private AreaController areaController;
    @Autowired
    private ContentTypeController contentTypeController;

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

        //시군구
        for(AreaParams areaParam: areaParams){
            String part2 = "&areaCode=" + areaParam.getCode();
            System.out.println(areaParam.getCode());

            JSONArray sigungu_list = getJson("/areaCode", part2);
            List<AreaParams> sigunguParams = new ArrayList();
            for (Object o : sigungu_list) {
                JSONObject item = (JSONObject) o;
                Long code = (Long) item.get("code");
                String name = (String) item.get("name");

                AreaParams sigunguParam = new AreaParams(code, name);
                sigunguParams.add(sigunguParam);
            }
            areaController.createSigungu(areaParam.getCode(), areaParam.getName(), sigunguParams);
        }

        //서비스 분류 -관광지
        JSONArray cat1_list1 = getJson("/categoryCode", "&contentTypeId=12");
        List<ContentTypeParams> cat1Params = new ArrayList();
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

                    ContentTypeParams2 contentTypeParams2 = new ContentTypeParams2(code1, name1, code2, name2, code3, name3);
                    contentTypeController.createContentType1(contentTypeParams2);
                }
            }
        }

        //서비스 분류 -음식
        JSONArray cat1_list2 = getJson("/categoryCode", "&contentTypeId=39");
        List<ContentTypeParams> cat1Params2 = new ArrayList();
        for (Object o1 : cat1_list2) {
            JSONObject item1 = (JSONObject) o1;
            String code1 = (String) item1.get("code");
            String name1 = (String) item1.get("name");

            JSONArray cat2_list2 = getJson("/categoryCode", "&cat1=" + code1 + "&contentTypeId=39");
            for (Object o2 : cat2_list2) {
                JSONObject item2 = (JSONObject) o2;
                String code2 = (String) item2.get("code");
                String name2 = (String) item2.get("name");

                JSONArray cat3_list2 = getJson("/categoryCode", "&cat1=" + code1 + "&cat2=" + code2 + "&contentTypeId=39");
                for (Object o3 : cat3_list2) {
                    JSONObject item3 = (JSONObject) o3;
                    String code3 = (String) item3.get("code");
                    String name3 = (String) item3.get("name");

                    ContentTypeParams2 contentTypeParams2 = new ContentTypeParams2(code1, name1, code2, name2, code3, name3);
                    contentTypeController.createContentType2(contentTypeParams2);
                }
            }
        }



    };


    //open api 호출해서 결과 리턴하는 함수
    public JSONArray getJson(String part1, String part2){

        String key = "?ServiceKey=BdxNGWQJQFutFYE6DkjePTmerMbwG2fzioTf6sr69ecOAdLGMH4iiukF8Ex93YotSgkDOHe1VxKNOr8USSN6EQ%3D%3D"; //인증키
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
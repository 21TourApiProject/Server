package com.server.tourApiProject.touristPoint;

import com.server.tourApiProject.touristPoint.area.AreaController;
import com.server.tourApiProject.touristPoint.area.AreaParams;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeController;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeParams;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

//서버가 초기화된후 바로 실행되는 코드
@Component
public class getOpenApiData implements org.springframework.boot.ApplicationRunner {

    @Autowired
    private AreaController areaController;
    @Autowired
    private ContentTypeController contentTypeController;
    @Autowired
    private TouristDataController touristDataController;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        //지역
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
//
//
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
//
//        //서비스 분류 - 음식
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

        int num = 0;

        //관광지
        JSONArray tour_list = getJson("/areaBasedList", "&listYN=Y&arrange=A&contentTypeId=12"); //관광 정보
        for (Object o : tour_list) {
            if (num > 1){
                break;
            }
            num += 1;
            JSONObject item = (JSONObject) o;
            TouristData touristData = getTouristData(item);
            Long contentId = (Long) item.get("contentid"); //컨텐츠ID

            JSONArray comm_list = getJson("/detailCommon", "&defaultYN=Y&overviewYN=Y&contentId=" + contentId); //공통 정보
            JSONObject comm = (JSONObject) comm_list.get(0);
            touristData.setHomePage((String) comm.get("homepage"));
            touristData.setOverview((String) comm.get("overview"));

            JSONArray intro_list = getJson("/detailIntro", "&contentTypeId=12&contentId=" + contentId); //소개 정보
            JSONObject intro = (JSONObject) intro_list.get(0);
            touristData.setUseTime((String) intro.get("usetime"));
            touristData.setRestDate((String) intro.get("restdate"));
            touristData.setExpGuide((String) intro.get("expguide"));
            touristData.setParking((String) intro.get("parking"));
            touristData.setChkPet((String) intro.get("chkpet"));

            touristDataController.createTouristData(touristData);
        }
        int num2 = 0;

        //음식
        JSONArray food_list = getJson("/areaBasedList", "&listYN=Y&arrange=A&contentTypeId=39"); //관광 정보
        for (Object o : food_list) {
            if (num2 > 1){
                break;
            }
            num2 += 1;
            JSONObject item = (JSONObject) o;
            TouristData touristData = getTouristData(item);
            Long contentId = (Long) item.get("contentid"); //컨텐츠ID

            JSONArray comm_list = getJson("/detailCommon", "&overviewYN=Y&contentId=" + contentId); //공통 정보
            JSONObject comm = (JSONObject) comm_list.get(0);
            touristData.setOverview((String) comm.get("overview"));

            JSONArray intro_list = getJson("/detailIntro", "&contentTypeId=39&contentId=" + contentId); //소개 정보
            JSONObject intro = (JSONObject) intro_list.get(0);
            touristData.setOpenTimeFood((String) intro.get("opentimefood"));
            touristData.setRestDateFood((String) intro.get("restdatefood"));
            touristData.setFirstMenu((String) intro.get("firstmenu"));
            touristData.setTreatMenu((String) intro.get("treatmenu"));
            touristData.setPacking((String) intro.get("packing"));
            touristData.setParkingFood((String) intro.get("parkingfood"));

            touristDataController.createTouristData(touristData);
        }

    }

    private TouristData getTouristData(JSONObject item) {
        TouristData touristData = new TouristData();
        if (item.get("addr1") != null)
            touristData.setAddr1((String) item.get("addr1"));

        if (item.get("addr2") != null) {
            if (item.get("addr2").getClass().getName().equals("java.lang.String")){
                touristData.setAddr2((String) item.get("addr2"));
            }
            else if (item.get("addr2").getClass().getName().equals("java.lang.Long")){
                touristData.setAddr2(String.valueOf(item.get("addr2")));
            }
        }

        if (item.get("areacode") != null)
            touristData.setAreaCode((Long) item.get("areacode"));
        if (item.get("readcount") != null)
            touristData.setReadCount((Long) item.get("readcount"));
        if (item.get("cat1") != null)
            touristData.setCat1((String) item.get("cat1"));
        if (item.get("cat2") != null)
            touristData.setCat2((String) item.get("cat2"));
        if (item.get("cat1") != null)
            touristData.setCat3((String) item.get("cat3"));
        if (item.get("contentid") != null)
            touristData.setContentId((Long) item.get("contentid"));
        if (item.get("contenttypeid") != null)
            touristData.setContentTypeId((Long) item.get("contenttypeid"));
        if (item.get("firstimage") != null)
            touristData.setFirstImage((String) item.get("firstimage"));
        if (item.get("firstimage2") != null)
            touristData.setFirstImage2((String) item.get("firstimage2"));

        if (item.get("mapx") != null) {
            if (item.get("mapx").getClass().getName().equals("java.lang.Double")){
                touristData.setMapX((Double) item.get("mapx"));
            }
            else if (item.get("mapx").getClass().getName().equals("java.lang.String")){
                touristData.setMapX(Double.valueOf((String) item.get("mapx")));
            }
        }

        if (item.get("mapy") != null) {
            if (item.get("mapy").getClass().getName().equals("java.lang.Double")){
                touristData.setMapY((Double) item.get("mapy"));
            }
            else if (item.get("mapy").getClass().getName().equals("java.lang.String")){
                touristData.setMapY(Double.valueOf((String) item.get("mapy")));
            }
        }

        if (item.get("sigungucode") != null)
            touristData.setSigunguCode((Long) item.get("sigungucode"));
        if (item.get("tel") != null)
            touristData.setTel((String) item.get("tel"));
        if (item.get("title") != null)
            touristData.setTitle((String) item.get("title"));

        if (item.get("zipcode") != null) {
            if (item.get("zipcode").getClass().getName().equals("java.lang.Long")){
                touristData.setZipcode((Long) item.get("zipcode"));
            }
            else if (item.get("zipcode").getClass().getName().equals("java.lang.String")){
                touristData.setZipcode(Long.valueOf((String) item.get("zipcode")));
            }
        }

        return touristData;
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
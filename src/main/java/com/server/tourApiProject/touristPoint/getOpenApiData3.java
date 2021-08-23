package com.server.tourApiProject.touristPoint;

import com.server.tourApiProject.touristPoint.nearTouristData.NearTouristDataController;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataController;
import com.server.tourApiProject.touristPoint.touristData.TouristDataRepository;
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
import java.util.List;

@Order(2)
@Component
public class getOpenApiData3 implements org.springframework.boot.ApplicationRunner {

    @Autowired
    private TouristDataController touristDataController;
    @Autowired
    private TouristDataRepository touristDataRepository;
    @Autowired
    private NearTouristDataController nearTouristDataController;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("order3");

        Double [][] tourXY = new Double[9548][2];
        Long [] tourId = new Long[9548];
        int t = 0;

        Double [][] foodXY = new Double[6333][2];
        Long [] foodId = new Long[6333];
        int f = 0;

        //관광지
//        List<Long> touristPointId = touristDataController.getTouristPointId();
//        for (Long contentId : touristPointId){
//
//            TouristData touristData = touristDataRepository.findByContentId(contentId);
//
//            JSONArray comm_list = getJson("/detailCommon", "&defaultYN=Y&overviewYN=Y&contentId=" + contentId, false); //공통 정보
//            JSONObject comm = (JSONObject) comm_list.get(0);
//
//            String tmp;
//            tmp = (String) comm.get("homepage");
//            if (tmp == null) {
//                touristData.setHomePage(null);
//            } else if (tmp.isEmpty()){
//                touristData.setHomePage(null);
//            }else{
//                touristData.setHomePage(extractHomePage(tmp));
//            }
//
//            tmp = (String) comm.get("overview");
//            if (tmp == null) {
//                touristData.setOverview(null);
//            } else if (tmp.isEmpty()){
//                touristData.setOverview(null);
//            }else{
//                touristData.setOverview(extractString(tmp));
//            }
//
//            JSONArray intro_list = getJson("/detailIntro", "&contentTypeId=12&contentId=" + contentId, false); //소개 정보
//            JSONObject intro = (JSONObject) intro_list.get(0);
//
//            if (intro.get("usetime") != null) {
//                if (intro.get("usetime").getClass().getName().equals("java.lang.String")){
//                    touristData.setUseTime((String) intro.get("usetime"));
//                }
//                else if (intro.get("usetime").getClass().getName().equals("java.lang.Long")){
//                    touristData.setUseTime(String.valueOf(intro.get("usetime")));
//                }
//            }
//
//            tmp = (String) intro.get("restdate");
//            if (tmp == null) {
//                touristData.setRestDate(null);
//            } else if (tmp.isEmpty()){
//                touristData.setRestDate(null);
//            }else{
//                touristData.setRestDate(extractString(tmp));
//            }
//
//            tmp = (String) intro.get("expguide");
//            if (tmp == null) {
//                touristData.setExpGuide(null);
//            } else if (tmp.isEmpty()){
//                touristData.setExpGuide(null);
//            }else{
//                touristData.setExpGuide(extractString(tmp));
//            }
//
//            tmp = (String) intro.get("parking");
//            if (tmp == null) {
//                touristData.setParking(null);
//            } else if (tmp.isEmpty()){
//                touristData.setParking(null);
//            }else{
//                touristData.setParking(extractString(tmp));
//            }
//
//            tmp = (String) intro.get("chkpet");
//            if (tmp == null) {
//                touristData.setChkPet(null);
//            } else if (tmp.isEmpty()){
//                touristData.setChkPet(null);
//            }else{
//                touristData.setChkPet(extractString(tmp));
//            }
//            touristData.setIsCom(1);
//            touristDataRepository.save(touristData);
//
//        }

//        for (int i = 0; i<9548; i++){
//            if (tourXY[i][0] != null){
//                String part2 = "&mapX=" + Double.toString(tourXY[i][0]) + "&mapY=" + Double.toString(tourXY[i][1]) + "&radius=20000&listYN=Y&arrange=S&numOfRows=4&contentTypeId=12";
//                JSONArray near_list = getJson("/locationBasedList", part2, true); //주변 정보
//                for (int j = 1; j < 4; j++) {
//                    JSONObject near = (JSONObject) near_list.get(j);
//                    nearTouristDataController.createNearTouristData(tourId[i], (Long) near.get("contentid"));
//                }
//            }
//        }


        //음식
//                JSONArray food_list = getJson("/areaBasedList", "&listYN=Y&arrange=A&contentTypeId=39", false); //관광 정보
//                for (Object o : food_list) {
//                    if(f<280)
//                        continue;
//                    JSONObject item = (JSONObject) o;
//                    TouristData touristData = getTouristData(item);
//                    Long contentId = (Long) item.get("contentid"); //컨텐츠ID
//                    System.out.println("contentId = " + contentId + " " +f);
//
//                    JSONArray comm_list = getJson("/detailCommon", "&overviewYN=Y&contentId=" + contentId, false); //공통 정보
//                    JSONObject comm = (JSONObject) comm_list.get(0);
//
//                    String tmp;
//                    tmp = (String) comm.get("overview");
//                    if (tmp == null) {
//                        touristData.setOverview(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setOverview(null);
//                    }else{
//                        touristData.setOverview(extractString(tmp));
//                    }
//
//                    JSONArray intro_list = getJson("/detailIntro", "&contentTypeId=39&contentId=" + contentId, false); //소개 정보
//                    JSONObject intro = (JSONObject) intro_list.get(0);
//
//                    tmp = (String) intro.get("opentimefood");
//                    if (tmp == null) {
//                        touristData.setOpenTimeFood(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setOpenTimeFood(null);
//                    }else{
//                        touristData.setOpenTimeFood(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("restdatefood");
//                    if (tmp == null) {
//                        touristData.setRestDateFood(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setRestDateFood(null);
//                    }else{
//                        touristData.setRestDateFood(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("firstmenu");
//                    if (tmp == null) {
//                        touristData.setFirstMenu(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setFirstMenu(null);
//                    }else{
//                        touristData.setFirstMenu(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("treatmenu");
//                    if (tmp == null) {
//                        touristData.setTreatMenu(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setTreatMenu(null);
//                    }else{
//                        touristData.setTreatMenu(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("packing");
//                    if (tmp == null) {
//                        touristData.setPacking(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setPacking(null);
//                    }else{
//                        touristData.setPacking(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("parkingfood");
//                    if (tmp == null) {
//                        touristData.setParkingFood(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setParkingFood(null);
//                    }else{
//                        touristData.setParkingFood(extractString(tmp));
//                    }
//
//                    List<Double> xny = touristDataController.createTouristData(touristData);
//                    foodXY[f][0] = xny.get(0);
//                    foodXY[f][1] = xny.get(1);
//                    foodId[f] = contentId;
//                    f++;
//                }
//                for (int i = 0; i<6336; i++) {
//                    if (foodXY[i][0] != null){
//                        String part2 = "&mapX=" + Double.toString(foodXY[i][0]) + "&mapY=" + Double.toString(foodXY[i][1]) + "&radius=20000&listYN=Y&arrange=S&numOfRows=4&contentTypeId=39";
//                        JSONArray near_list = getJson("/locationBasedList", part2, true); //주변 정보
//                        System.out.println("near_list.size() = " + near_list.size());
//                        for (int j = 1; j < near_list.size(); j++) {
//                            JSONObject near = (JSONObject) near_list.get(j);
//                            nearTouristDataController.createNearTouristData(foodId[i], (Long) near.get("contentid"));
//                        }
//                    }
//                }

    }

    public String extractHomePage(String url){
        if (url.contains("href=\"")){
            int start = url.indexOf("href=\"");
            int end = url.indexOf("\"", start+6);
            return url.substring(start+6, end);
        }
        else{
            return url;
        }
    }

    public String extractString(String overview){
        overview = overview.replaceAll("<br>","");
        overview = overview.replaceAll("<br />"," ");
        overview = overview.replaceAll("<br/>"," ");
        overview = overview.replaceAll("<strong>","");
        overview = overview.replaceAll("</strong>","");
        overview = overview.replaceAll("\n"," ");

        return overview;
    }


    //open api 호출해서 결과 리턴하는 함수
    public JSONArray getJson(String part1, String part2, Boolean isNear){

        String key = "?ServiceKey=BdxNGWQJQFutFYE6DkjePTmerMbwG2fzioTf6sr69ecOAdLGMH4iiukF8Ex93YotSgkDOHe1VxKNOr8USSN6EQ=="; //인증키
        String result = "";

        try{
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService" + part1 + key + part2 + "&MobileOS=AND&MobileApp=tourApiProject&_type=json");
            if(isNear)
                System.out.println("url = " + url);
            BufferedReader bf; //빠른 속도로 데이터를 처리하기 위해
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine(); //api로 받아온 결과

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject response = (JSONObject)jsonObject.get("response");
            JSONObject body = (JSONObject)response.get("body");
            Long count = (Long)body.get("totalCount");

            if (count == 0){
                JSONObject item = new JSONObject();
                JSONArray resultForZero = new JSONArray();
                resultForZero.add(item);
                return resultForZero;
            }
            JSONObject items = (JSONObject)body.get("items");

            if (isNear){
                count = 4L;
            }

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

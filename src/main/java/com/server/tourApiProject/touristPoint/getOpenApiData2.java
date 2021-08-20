package com.server.tourApiProject.touristPoint;

import com.server.tourApiProject.DynamicScheduledConfig;
import com.server.tourApiProject.touristPoint.nearTouristData.NearTouristDataController;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Order(2)
@Component
public class getOpenApiData2 implements org.springframework.boot.ApplicationRunner {

    @Autowired
    private TouristDataController touristDataController;
    @Autowired
    private NearTouristDataController nearTouristDataController;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        DynamicScheduledConfig scheduledConfig = new DynamicScheduledConfig() {
            @Override
            public void runner() {
                System.out.println("매일 03시마다 실행");
                touristDataController.deleteTouristData();

                Double [][] tourXY = new Double[9569][2];
                Long [] tourId = new Long[9569];
                int t = 0;

                Double [][] foodXY = new Double[6336][2];
                Long [] foodId = new Long[6336];
                int f = 0;

                //관광지
                JSONArray tour_list = getJson("/areaBasedList", "&listYN=Y&arrange=A&contentTypeId=12", true); //관광 정보
                for (Object o : tour_list) {

                    JSONObject item = (JSONObject) o;
                    TouristData touristData = getTouristData(item);
                    Long contentId = (Long) item.get("contentid"); //컨텐츠ID

                    JSONArray comm_list = getJson("/detailCommon", "&defaultYN=Y&overviewYN=Y&contentId=" + contentId, true); //공통 정보
                    JSONObject comm = (JSONObject) comm_list.get(0);

                    if ((String) comm.get("homepage") != null){
                        touristData.setHomePage(extractHomePage((String) comm.get("homepage")));
                    } else{
                        touristData.setHomePage((String) comm.get("homepage"));
                    }
                    if ((String) comm.get("overview") != null){
                        touristData.setOverview(extractString((String) comm.get("overview")));
                    } else{
                        touristData.setOverview((String) comm.get("overview"));
                    }

                    JSONArray intro_list = getJson("/detailIntro", "&contentTypeId=12&contentId=" + contentId, true); //소개 정보
                    JSONObject intro = (JSONObject) intro_list.get(0);

                    if ((String) intro.get("usetime") != null){
                        touristData.setUseTime(extractString((String) intro.get("usetime")));
                    } else{
                        touristData.setUseTime((String) intro.get("usetime"));
                    }

                    if ((String) intro.get("restdate") != null){
                        touristData.setRestDate(extractString((String) intro.get("restdate")));
                    } else{
                        touristData.setRestDate((String) intro.get("restdate"));
                    }

                    if ((String) intro.get("expguide") != null){
                        touristData.setExpGuide(extractString((String) intro.get("expguide")));
                    } else{
                        touristData.setExpGuide((String) intro.get("expguide"));
                    }

                    if ((String) intro.get("parking") != null){
                        touristData.setParking(extractString((String) intro.get("parking")));
                    } else{
                        touristData.setParking((String) intro.get("parking"));
                    }

                    if ((String) intro.get("chkpet") != null){
                        touristData.setChkPet(extractString((String) intro.get("chkpet")));
                    } else{
                        touristData.setChkPet((String) intro.get("chkpet"));
                    }
                    System.out.println("contentId = " + contentId);
                    List<Double> xny = touristDataController.createTouristData(touristData);
                    tourXY[t][0] = xny.get(0);
                    tourXY[t][1] = xny.get(1);
                    tourId[t] = contentId;
                    t++;
                }
                for (int i = 0; i<9569; i++){
                    String part2 = "&mapX=" + Double.toString(tourXY[i][0]) + "&mapY=" + Double.toString(tourXY[i][1]) + "&radius=20000&listYN=Y&arrange=S&numOfRows=4&contentTypeId=12";
                    JSONArray near_list = getJson("/locationBasedList", part2, false); //주변 정보
                    for (int j = 1; j < 4; j++) {
                        JSONObject near = (JSONObject) near_list.get(j);
                        nearTouristDataController.createNearTouristData(tourId[i], (Long) near.get("contentid"));
                    }
                }



                //음식
                JSONArray food_list = getJson("/areaBasedList", "&listYN=Y&arrange=A&contentTypeId=39", true); //관광 정보
                for (Object o : food_list) {
                    JSONObject item = (JSONObject) o;
                    TouristData touristData = getTouristData(item);
                    Long contentId = (Long) item.get("contentid"); //컨텐츠ID

                    JSONArray comm_list = getJson("/detailCommon", "&overviewYN=Y&contentId=" + contentId, true); //공통 정보
                    JSONObject comm = (JSONObject) comm_list.get(0);

                    if ((String) comm.get("overview") != null){
                        touristData.setOverview(extractString((String) comm.get("overview")));
                    } else{
                        touristData.setOverview((String) comm.get("overview"));
                    }

                    JSONArray intro_list = getJson("/detailIntro", "&contentTypeId=39&contentId=" + contentId, true); //소개 정보
                    JSONObject intro = (JSONObject) intro_list.get(0);

                    if ((String) intro.get("opentimefood") != null){
                        touristData.setOpenTimeFood(extractString((String) intro.get("opentimefood")));
                    } else{
                        touristData.setOpenTimeFood((String) intro.get("opentimefood"));
                    }

                    if ((String) intro.get("restdatefood") != null){
                        touristData.setRestDateFood(extractString((String) intro.get("restdatefood")));
                    } else{
                        touristData.setRestDateFood((String) intro.get("restdatefood"));
                    }

                    if ((String) intro.get("firstmenu") != null){
                        touristData.setFirstMenu(extractString((String) intro.get("firstmenu")));
                    } else{
                        touristData.setFirstMenu((String) intro.get("firstmenu"));
                    }

                    if ((String) intro.get("treatmenu") != null){
                        touristData.setTreatMenu(extractString((String) intro.get("treatmenu")));
                    } else{
                        touristData.setTreatMenu((String) intro.get("treatmenu"));
                    }

                    if ((String) intro.get("packing") != null){
                        touristData.setPacking(extractString((String) intro.get("packing")));
                    } else{
                        touristData.setPacking((String) intro.get("packing"));
                    }

                    if ((String) intro.get("parkingfood") != null){
                        touristData.setParkingFood(extractString((String) intro.get("parkingfood")));
                    } else{
                        touristData.setParkingFood((String) intro.get("parkingfood"));
                    }

                    List<Double> xny = touristDataController.createTouristData(touristData);
                    foodXY[f][0] = xny.get(0);
                    foodXY[f][1] = xny.get(1);
                    foodId[f] = contentId;
                    f++;
                }
                for (int i = 0; i<6336; i++) {
                    String part2 = "&mapX=" + Double.toString(foodXY[i][0]) + "&mapY=" + Double.toString(foodXY[i][1]) + "&radius=20000&listYN=Y&arrange=S&numOfRows=4&contentTypeId=39";
                    JSONArray near_list = getJson("/locationBasedList", part2, false); //주변 정보
                    for (int j = 1; j < 4; j++) {
                        JSONObject near = (JSONObject) near_list.get(j);
                        nearTouristDataController.createNearTouristData(foodId[i], (Long) near.get("contentid"));
                    }
                }


            }

            @Override
            public Trigger getTrigger() {
                return new CronTrigger("10 18 13 * * ?");
            }
        };
        scheduledConfig.startScheduler();

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
        overview = overview.replaceAll("\n","");

        return overview;
    }

    public TouristData getTouristData(JSONObject item) {
        TouristData touristData = new TouristData();

        touristData.setAddr1((String) item.get("addr1"));
        if (item.get("addr2") != null) {
            if (item.get("addr2").getClass().getName().equals("java.lang.String")){
                touristData.setAddr2((String) item.get("addr2"));
            }
            else if (item.get("addr2").getClass().getName().equals("java.lang.Long")){
                touristData.setAddr2(String.valueOf(item.get("addr2")));
            }
        }
        touristData.setAreaCode((Long) item.get("areacode"));
        touristData.setCat1((String) item.get("cat1"));
        touristData.setCat2((String) item.get("cat2"));
        touristData.setCat3((String) item.get("cat3"));
        touristData.setContentId((Long) item.get("contentid"));
        touristData.setContentTypeId((Long) item.get("contenttypeid"));
        touristData.setFirstImage((String) item.get("firstimage"));
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
        touristData.setSigunguCode((Long) item.get("sigungucode"));

        if ((String) item.get("tel") != null){
            touristData.setTel(extractString((String) item.get("tel")));
        } else{
            touristData.setTel((String) item.get("tel"));
        }

        if ((String) item.get("title") != null){
            touristData.setTitle(extractString((String) item.get("title")));
        } else{
            touristData.setTitle((String) item.get("title"));
        }
//        if (item.get("zipcode") != null) {
//            if (item.get("zipcode").getClass().getName().equals("java.lang.Long")){
//                touristData.setZipcode((Long) item.get("zipcode"));
//            }
//            else if (item.get("zipcode").getClass().getName().equals("java.lang.String")){
//                touristData.setZipcode(Long.valueOf((String) item.get("zipcode")));
//            }
//        }
        return touristData;
    }


    //open api 호출해서 결과 리턴하는 함수
    public JSONArray getJson(String part1, String part2, Boolean isNotNear){

        String key = "?ServiceKey=BdxNGWQJQFutFYE6DkjePTmerMbwG2fzioTf6sr69ecOAdLGMH4iiukF8Ex93YotSgkDOHe1VxKNOr8USSN6EQ%3D%3D"; //인증키
        String result = "";

        try{
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService" + part1 + key + part2 + "&MobileOS=AND&MobileApp=tourApiProject&_type=json");
            if(!isNotNear)
                System.out.println("url = " + url);
            BufferedReader bf; //빠른 속도로 데이터를 처리하기 위해
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine(); //api로 받아온 결과

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject response = (JSONObject)jsonObject.get("response");
            JSONObject body = (JSONObject)response.get("body");
            JSONObject items = (JSONObject)body.get("items");
            Long count;
            if (isNotNear){
                count = (Long)body.get("totalCount");
            }else {
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
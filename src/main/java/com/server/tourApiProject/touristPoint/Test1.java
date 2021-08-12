package com.server.tourApiProject.touristPoint;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class Test1 {
    private static final String CONTENT_TYPE = "application/json";

    public static void main(String args[]){

        String key = "BdxNGWQJQFutFYE6DkjePTmerMbwG2fzioTf6sr69ecOAdLGMH4iiukF8Ex93YotSgkDOHe1VxKNOr8USSN6EQ%3D%3D"; //인증키
        String result = "";
        List<AreaParams> areaParams = new ArrayList();

        try{
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?ServiceKey=" + key +
                    "&MobileOS=AND&MobileApp=tourApiProject&_type=json");

            BufferedReader bf; //빠른 속도로 데이터를 처리하기 위해
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine(); //api로 받아온 결과
            //System.out.println("result = " + result);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject response = (JSONObject)jsonObject.get("response");
            JSONObject body = (JSONObject)response.get("body");
            JSONObject items = (JSONObject)body.get("items");
            JSONArray item_list = (JSONArray)items.get("item");

            //System.out.println("body = " + body);
            //System.out.println("items = " + items);
            System.out.println("결과 개수 : " + body.get("numOfRows"));

            for (Object o : item_list) {
                JSONObject item = (JSONObject) o;
                Long code = (Long) item.get("code");
                String name = (String) item.get("name");

                AreaParams areaParam = new AreaParams(code, name);
                areaParams.add(areaParam);
            }
            bf.close();

            List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
            converters.add(new FormHttpMessageConverter());
            converters.add(new StringHttpMessageConverter());

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setMessageConverters(converters);

            // REST API 호출
            restTemplate.postForObject("http://localhost:8080/v1/area", areaParams, String.class);

            }catch(Exception e){
            e.printStackTrace();
        }


    }

}

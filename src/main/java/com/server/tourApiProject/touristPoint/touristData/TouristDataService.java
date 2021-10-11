package com.server.tourApiProject.touristPoint.touristData;

import com.server.tourApiProject.myWish.MyWishParams01;
import com.server.tourApiProject.search.Filter;
import com.server.tourApiProject.search.SearchParams1;
import com.server.tourApiProject.touristPoint.contentType.ContentType;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeRepository;
import com.server.tourApiProject.touristPoint.touristDataHashTag.TouristDataHashTag;
import com.server.tourApiProject.touristPoint.touristDataHashTag.TouristDataHashTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TouristDataService {

    private final TouristDataRepository touristDataRepository;
    private final ContentTypeRepository contentTypeRepository;
    private final TouristDataHashTagRepository touristDataHashTagRepository;

    public void createTouristData(TouristData touristData) {
        touristDataRepository.save(touristData);
    }

    public Long getContentType(Long contentId) {
        TouristData touristData = touristDataRepository.findByContentId(contentId);
        return touristData.getContentTypeId();
    }

    public TouristDataParams getTouristPointData(Long contentId) {
        TouristData touristData = touristDataRepository.findByContentId(contentId);
        TouristDataParams result = new TouristDataParams();

        result.setContentTypeId(touristData.getContentTypeId()); //12
        result.setFirstImage(touristData.getFirstImage());
        result.setTitle(touristData.getTitle());
        result.setCat3Name(contentTypeRepository.findByCat3Code(touristData.getCat3()).getCat3Name());
        result.setOverview(touristData.getOverview());
        result.setAddr(touristData.getAddr());
        result.setTel(touristData.getTel());
        result.setUseTime(touristData.getUseTime());
        result.setRestDate(touristData.getRestDate());
        result.setExpGuide(touristData.getExpGuide());
        result.setParking(touristData.getParking());
        result.setChkPet(touristData.getChkPet());
        result.setHomePage(touristData.getHomePage());
        result.setMapX(touristData.getMapX());
        result.setMapY(touristData.getMapY());
        result.setOverviewSim(touristData.getOverviewSim());
        return result;
    }

    public TouristDataParams2 getFoodData(Long contentId) {
        TouristData touristData = touristDataRepository.findByContentId(contentId);
        TouristDataParams2 result = new TouristDataParams2();

        result.setContentTypeId(touristData.getContentTypeId()); //39
        result.setFirstImage(touristData.getFirstImage());
        result.setTitle(touristData.getTitle());
        result.setCat3Name(contentTypeRepository.findByCat3Code(touristData.getCat3()).getCat3Name());
        result.setOverview(touristData.getOverview());
        result.setAddr(touristData.getAddr());
        result.setTel(touristData.getTel());
        result.setOpenTimeFood(touristData.getOpenTimeFood());
        result.setRestDateFood(touristData.getRestDateFood());
        result.setFirstMenu(touristData.getFirstMenu());
        result.setTreatMenu(touristData.getTreatMenu());
        result.setPacking(touristData.getPacking());
        result.setParkingFood(touristData.getParkingFood());
        result.setMapX(touristData.getMapX());
        result.setMapY(touristData.getMapY());
        result.setOverviewSim(touristData.getOverviewSim());
        return result;
    }

    public void deleteTouristData() {
        touristDataRepository.deleteAll();
    }

    public List<Long> getTouristPointId() {
        List<TouristData> list = touristDataRepository.findByContentTypeId(12L);
        List<Long> result = new ArrayList<>();
        for (TouristData data : list){
            result.add(data.getContentId());
        }
        return result;
    }

    public List<Long> getFoodId() {
        List<TouristData> list = touristDataRepository.findByContentTypeId(39L);
        List<Long> result = new ArrayList<>();
        for (TouristData data : list){
            result.add(data.getContentId());
        }
        return result;
    }

    public Boolean isThere(Long contentId){
        Optional<TouristData> data = touristDataRepository.findById(contentId);
        return data.isPresent();
    }

    public Double [][] getTouristPointMap() {
        List<TouristData> list = touristDataRepository.findByContentTypeId(12L);
        Double [][] result = new Double[10000][2];
        int i = 0;
        for (TouristData data : list){
            result[i][0] = data.getMapX();
            result[i][1] = data.getMapY();
            i++;
        }
        return result;
    }

    public Double[][] getFoodMap() {
        List<TouristData> list = touristDataRepository.findByContentTypeId(39L);
        Double [][] result = new Double[7000][2];
        int i = 0;
        for (TouristData data : list){
            result[i][0] = data.getMapX();
            result[i][1] = data.getMapY();
            i++;
        }
        return result;

    }

    public void deleteTouristPoint() {
        List<TouristData> t12  = touristDataRepository.findByContentTypeId(12L);
        for (TouristData touristData : t12) {
            touristDataRepository.deleteById(touristData.getContentId());
        }
    }

    public Double[][] getTouristPointMap2() {
        List<TouristData> list = touristDataRepository.findByIsJu(0);
        Double [][] result = new Double[1000][2];
        int i = 0;
        for (TouristData data : list){
            if (data.getContentTypeId() != 12L)
                continue;
            result[i][0] = data.getMapX();
            result[i][1] = data.getMapY();
            i++;
        }
        return result;
    }

    public Double[][] getFoodMap2() {
        List<TouristData> list = touristDataRepository.findByIsJu(0);
        Double [][] result = new Double[700][2];
        int i = 0;
        for (TouristData data : list){
            if (data.getContentTypeId() != 39L)
                continue;
            result[i][0] = data.getMapX();
            result[i][1] = data.getMapY();
            i++;
        }
        return result;
    }

    public List<Long> getTouristPointId2() {
        List<TouristData> list = touristDataRepository.findByIsJu(0);
        List<Long> result = new ArrayList<>();
        for (TouristData data : list){
            if (data.getContentTypeId() != 12L)
                continue;
            result.add(data.getContentId());
        }
        return result;
    }

    public List<Long> getFoodId2() {
        List<TouristData> list = touristDataRepository.findByIsJu(0);
        List<Long> result = new ArrayList<>();
        for (TouristData data : list){
            if (data.getContentTypeId() != 39L)
                continue;
            result.add(data.getContentId());
        }
        return result;
    }

    public List<Long> getId4Image() {
        List<TouristData> list = touristDataRepository.findByFirstImage(null);
        List<Long> result = new ArrayList<>();
        for (TouristData data : list){
            result.add(data.getContentId());
        }
        return result;
    }

    public List<SearchParams1> getTouristPointWithFilter(Filter filter, String keyword) {
        List<Long> areaCodeList = filter.getAreaCodeList();    //지역 필터 리스트
        List<Long> hashTagIdList= filter.getHashTagIdList();    //해시태그 필터 리스트

        HashMap<String, String> cat3Map = new HashMap<>();
        List<ContentType> all = contentTypeRepository.findAll();
        for(ContentType contentType : all){
            cat3Map.put(contentType.getCat3Code(), contentType.getCat3Name());
        }

        HashMap<Long, Boolean> hashMap = new HashMap<>();
        if(hashTagIdList.size() != 0){
            for(Long id : hashTagIdList){
                hashMap.put(id, true);
            }
        }

        List<TouristData> result = new ArrayList<>();
        List<SearchParams1> resultParams = new ArrayList<>();   //결과

        if (areaCodeList.size() == 0){
            result = touristDataRepository.findByTitleContaining(keyword);
        }
        else{
            result = touristDataRepository.findByAreaCodesTitle(keyword, areaCodeList);
        }

        for (TouristData touristData : result){

            SearchParams1 searchParams1 = new SearchParams1();
            searchParams1.setItemId(touristData.getContentId());
            searchParams1.setTitle(touristData.getTitle());

            //주소를 두단어까지 줄임
            String address = touristData.getAddr();
            int i = address.indexOf(' ');
            if (i != -1){
                int j = address.indexOf(' ', i+1);
                if(j != -1){
                    searchParams1.setAddress(touristData.getAddr().substring(0, j));
                } else{
                    searchParams1.setAddress(touristData.getAddr());
                }
            } else{
                searchParams1.setAddress(touristData.getAddr());
            }

            searchParams1.setLatitude(touristData.getMapY());
            searchParams1.setLongitude(touristData.getMapX());
            searchParams1.setIntro(touristData.getOverviewSim());
            searchParams1.setContentType(cat3Map.get(touristData.getCat3()));
            searchParams1.setThumbnail(touristData.getFirstImage());

            List<TouristDataHashTag> touristDataHashTags = touristData.getTouristDataHashTags();
            Boolean isHashTagNoMatch = true;
            if(hashTagIdList.size() != 0){
                if(touristDataHashTags.size() == 0) {
                    continue;
                }
                for(TouristDataHashTag hashTag : touristDataHashTags){
                    if(hashMap.get(hashTag.getHashTagId()) != null && hashMap.get(hashTag.getHashTagId())) {
                        isHashTagNoMatch = false;
                        break;
                    }
                }
            }
            if(hashTagIdList.size() != 0 && isHashTagNoMatch)
                continue;

            int j = 0;
            List<String> hashTagNames = new ArrayList<>();
            for (TouristDataHashTag hashTag : touristDataHashTags){
                if (j > 2)
                    break;
                hashTagNames.add(hashTag.getHashTagName());
                j++;
            }
            searchParams1.setHashTagNames(hashTagNames);
            resultParams.add(searchParams1);
        }

        return resultParams;
    }
}

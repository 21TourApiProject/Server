package com.server.tourApiProject.touristPoint.touristData;

import com.server.tourApiProject.myWish.MyWishParams01;
import com.server.tourApiProject.observation.course.CourseRepository;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeRepository;
import com.server.tourApiProject.touristPoint.touristDataHashTag.TouristDataHashTag;
import com.server.tourApiProject.touristPoint.touristDataHashTag.TouristDataHashTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TouristDataService {

    private final TouristDataRepository touristDataRepository;
    private final ContentTypeRepository contentTypeRepository;
    private final CourseRepository courseRepository;
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
        result.setAddr1(touristData.getAddr1());
        result.setTel(touristData.getTel());
        result.setUseTime(touristData.getUseTime());
        result.setRestDate(touristData.getRestDate());
        result.setExpGuide(touristData.getExpGuide());
        result.setParking(touristData.getParking());
        result.setChkPet(touristData.getChkPet());
        result.setHomePage(touristData.getHomePage());
        return result;
    }

    public TouristDataParams2 getFoodData(Long contentId) {
        TouristData touristData = touristDataRepository.findByContentId(contentId);
        TouristDataParams2 result = new TouristDataParams2();

        result.setContentTypeId(touristData.getContentTypeId()); //39
        result.setTitle(touristData.getTitle());
        result.setCat3Name(contentTypeRepository.findByCat3Code(touristData.getCat3()).getCat3Name());
        result.setOverview(touristData.getOverview());
        result.setAddr1(touristData.getAddr1());
        result.setTel(touristData.getTel());
        result.setOpenTimeFood(touristData.getOpenTimeFood());
        result.setRestDateFood(touristData.getRestDateFood());
        result.setFirstMenu(touristData.getFirstMenu());
        result.setTreatMenu(touristData.getTreatMenu());
        result.setPacking(touristData.getPacking());
        result.setParkingFood(touristData.getParkingFood());
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

    public List<MyWishParams01> getTouristDataWithFilter(List<Long> areaCodeList, List<Long> hashTagIdList) {

        List<MyWishParams01> result = new ArrayList<>();
        List<Long> contentIdList = new ArrayList<>();

        for(Long areaCode : areaCodeList){
            List<TouristData> touristDatas = touristDataRepository.findByAreaCode(areaCode);
            for (TouristData touristData : touristDatas) {
                Long contentId = touristData.getContentId();
                if (!contentIdList.contains(contentId)) { //관광지 중복 제거
                    contentIdList.add(contentId);
                }
            }
        }
        for(Long hashTagId : hashTagIdList){
            List<TouristDataHashTag> touristDataHashTags = touristDataHashTagRepository.findByHashTagId(hashTagId);
            for (TouristDataHashTag touristDataHashTag : touristDataHashTags) {
                Long contentId = touristDataHashTag.getContentId();
                if (!contentIdList.contains(contentId)) { //관광지 중복 제거
                    contentIdList.add(contentId);
                }
            }
        }

        for (Long contentId : contentIdList){
            TouristData touristData = touristDataRepository.findById(contentId).orElseThrow(IllegalAccessError::new);
            MyWishParams01 myWishParams01 = new MyWishParams01();
            myWishParams01.setItemId(contentId);
            myWishParams01.setThumbnail(touristData.getFirstImage());
            myWishParams01.setTitle(touristData.getTitle());
            myWishParams01.setAddress(touristData.getAddr1());
            myWishParams01.setCat3Name(contentTypeRepository.findByCat3Code(touristData.getCat3()).getCat3Name());
            myWishParams01.setOverviewSim(touristData.getOverviewSim());

            List<TouristDataHashTag> hashTagList = touristDataHashTagRepository.findByContentId(contentId);
            List<String> hashTagNames = new ArrayList<>();
            for (TouristDataHashTag hashTag : hashTagList){
                hashTagNames.add(hashTag.getHashTagName());
            }
            myWishParams01.setHashTagNames(hashTagNames);
            result.add(myWishParams01);
        }
        return result;
    }
}

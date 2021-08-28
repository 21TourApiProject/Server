package com.server.tourApiProject.touristPoint.touristData;

import com.server.tourApiProject.touristPoint.contentType.ContentTypeRepository;
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
        List<TouristData> list = touristDataRepository.findByIsNearAndContentTypeId(0, 12L);
        Double [][] result = new Double[1000][2];
        int i = 0;
        for (TouristData data : list){
            result[i][0] = data.getMapX();
            result[i][1] = data.getMapY();
            i++;
        }
        return result;
    }

    public Double[][] getFoodMap2() {
        List<TouristData> list = touristDataRepository.findByIsNearAndContentTypeId(0, 39L);
        Double [][] result = new Double[700][2];
        int i = 0;
        for (TouristData data : list){
            result[i][0] = data.getMapX();
            result[i][1] = data.getMapY();
            i++;
        }
        return result;
    }

    public List<Long> getTouristPointId2() {
        List<TouristData> list = touristDataRepository.findByIsNearAndContentTypeId(0,12L);
        List<Long> result = new ArrayList<>();
        for (TouristData data : list){
            result.add(data.getContentId());
        }
        return result;
    }

    public List<Long> getFoodId2() {
        List<TouristData> list = touristDataRepository.findByIsNearAndContentTypeId(0,39L);
        List<Long> result = new ArrayList<>();
        for (TouristData data : list){
            result.add(data.getContentId());
        }
        return result;
    }
}

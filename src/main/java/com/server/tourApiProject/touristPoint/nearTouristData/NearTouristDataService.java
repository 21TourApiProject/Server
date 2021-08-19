package com.server.tourApiProject.touristPoint.nearTouristData;

import com.server.tourApiProject.touristPoint.contentType.ContentTypeRepository;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NearTouristDataService {

    private final NearTouristDataRepository nearTouristDataRepository;
    private final TouristDataRepository touristDataRepository;
    private final ContentTypeRepository contentTypeRepository;

    public void createNearTouristData(Long contentId1, Long contentId2) {
        NearTouristData nearTouristData = new NearTouristData();
        nearTouristData.setTouristDataId(contentId1);
        nearTouristData.setTouristData(touristDataRepository.findByContentId(contentId1));
        nearTouristData.setContentId(contentId2);

        TouristData me = touristDataRepository.findByContentId(contentId2);
        if (me.getFirstImage() != null){
            nearTouristData.setFirstImage(me.getFirstImage());
        }
        if (me.getOverview() != null){
            nearTouristData.setOverviewSimple(me.getOverview().substring(0,15)+"..."); //짧은 개요 나중에 제대로 수정
        }
        nearTouristData.setTitle(me.getTitle());
        nearTouristData.setAddr1(me.getAddr1());
        nearTouristData.setCat3Name(contentTypeRepository.findByCat3Code(me.getCat3()).getCat3Name());
        nearTouristDataRepository.save(nearTouristData);
    }

    public List<NearTouristData> getNearTouristData(Long contentId) {
        return nearTouristDataRepository.findByTouristDataId(contentId);
    }
}

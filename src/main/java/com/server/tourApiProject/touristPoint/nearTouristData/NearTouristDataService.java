package com.server.tourApiProject.touristPoint.nearTouristData;

import com.server.tourApiProject.touristPoint.contentType.ContentTypeRepository;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataRepository;
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
public class NearTouristDataService {

    private final NearTouristDataRepository nearTouristDataRepository;
    private final TouristDataRepository touristDataRepository;
    private final TouristDataHashTagRepository touristDataHashTagRepository;
    private final ContentTypeRepository contentTypeRepository;

    public void createNearTouristData(Long contentId1, Long contentId2) {
        Optional<TouristData> data = touristDataRepository.findById(contentId2);
        if (data.isEmpty())
            return;

        NearTouristData nearTouristData = new NearTouristData();
        nearTouristData.setTouristDataId(contentId1);
        nearTouristData.setTouristData(touristDataRepository.findByContentId(contentId1));
        nearTouristData.setContentId(contentId2);

        TouristData me = touristDataRepository.findByContentId(contentId2);
        if (me.getFirstImage() != null){
            nearTouristData.setFirstImage(me.getFirstImage());
        }
        if (me.getOverviewSim() != null){
            nearTouristData.setOverviewSim(me.getOverviewSim());
        }
        nearTouristData.setTitle(me.getTitle());
        nearTouristData.setAddr(me.getAddr());
        nearTouristData.setCat3Name(contentTypeRepository.findByCat3Code(me.getCat3()).getCat3Name());
        nearTouristDataRepository.save(nearTouristData);
    }

    public List<NearTouristDataParams> getNearTouristData(Long contentId) {
        List<NearTouristData> dataList = nearTouristDataRepository.findByTouristDataId(contentId);
        List<NearTouristDataParams> result = new ArrayList<>();
        for (NearTouristData data : dataList){
            NearTouristDataParams param = new NearTouristDataParams();
            param.setContentId(data.getContentId());
            param.setFirstImage(data.getFirstImage());
            param.setTitle(data.getTitle());
            param.setAddr(data.getAddr());
            param.setCat3Name(data.getCat3Name());
            param.setOverviewSim(data.getOverviewSim());

            List<String> hashTagNames= new ArrayList<>();
            List<TouristDataHashTag> hashTagList = touristDataHashTagRepository.findByContentId(data.getContentId());
            for(TouristDataHashTag hashTag : hashTagList){
                hashTagNames.add(hashTag.getHashTagName());
            }
            param.setHashTagNames(hashTagNames);
            result.add(param);
        }
        return result;
    }

    public void deleteNearTouristData() {
        nearTouristDataRepository.deleteAll();
    }

    public void deleteNearTouristPoint() {
        List<TouristData> t12  = touristDataRepository.findByContentTypeId(12L);
        for (TouristData touristData : t12) {
            touristDataRepository.deleteById(touristData.getContentId());
        }
    }
}

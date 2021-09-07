package com.server.tourApiProject.touristPoint.touristDataHashTag;

import com.server.tourApiProject.myWish.MyWishParams01;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeRepository;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TouristDataHashTagService {

    private final TouristDataHashTagRepository touristDataHashTagRepository;
    private final TouristDataRepository touristDataRepository;
    private final ContentTypeRepository contentTypeRepository;

    public List<String> getTouristDataHashTag(Long contentId) {
        List<TouristDataHashTag> list = touristDataHashTagRepository.findByContentId(contentId);
        List<String> result = new ArrayList<>();
        for(TouristDataHashTag dataHashTag : list){
            result.add(dataHashTag.getHashTagName());
        }
        return result;
    }

    public List<MyWishParams01> getTouristDataWithHashTag(List<Long> hashTagIdList) {
        List<MyWishParams01> result = new ArrayList<>();
        List<Long> contentIdList = new ArrayList<>();

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

package com.server.tourApiProject.touristPoint.touristDataHashTag;

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

    public List<String> getTouristDataHashTag(Long contentId) {
        List<TouristDataHashTag> list = touristDataHashTagRepository.findByContentId(contentId);
        List<String> result = new ArrayList<>();
        for(TouristDataHashTag dataHashTag : list){
            result.add(dataHashTag.getHashTagName());
        }
        return result;
    }
}

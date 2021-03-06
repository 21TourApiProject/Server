package com.server.tourApiProject.observation;

import com.server.tourApiProject.hashTag.HashTag;
import com.server.tourApiProject.hashTag.HashTagRepository;
import com.server.tourApiProject.myWish.MyWishParams01;
import com.server.tourApiProject.observation.observeImage.ObserveImage;
import com.server.tourApiProject.observation.observeImage.ObserveImageRepository;
import com.server.tourApiProject.search.SearchParams1;
import com.server.tourApiProject.observation.observeFee.ObserveFee;
import com.server.tourApiProject.observation.observeFee.ObserveFeeRepository;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTag;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTagParams;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTagRepository;
import com.server.tourApiProject.search.Filter;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristDataHashTag.TouristDataHashTag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ObservationService {
    private final ObservationRepository observationRepository;
    private final ObserveHashTagRepository observeHashTagRepository;
    private final HashTagRepository hashTagRepository;
    private final ObserveFeeRepository observeFeeRepository;
    private final ObserveImageRepository observeImageRepository;

    public List<Observation> getAllObservation() {
        return observationRepository.findAll();
    }

    public void createObservation(ObservationParams observationParams) {
        Observation observation = new Observation();
        observation.setObservationName(observationParams.getObservationName());
        observation.setLink(observationParams.getLink());
        observation.setLatitude(observationParams.getLatitude());
        observation.setLongitude(observationParams.getLongitude());
        observation.setAddress(observationParams.getAddress());
        observation.setPhoneNumber(observationParams.getPhoneNumber());
        observation.setOperatingHour(observationParams.getOperatingHour());
        observation.setParking(observationParams.getParking());
        observation.setObserveType(observationParams.getObserveType());
        observation.setOutline(observationParams.getOutline());
        observationRepository.save(observation);
    }

    public void createObserveHashTags(Long observationId, List<ObserveHashTagParams> observeHashTagParams) {
        Observation observation = observationRepository.findById(observationId).orElseThrow(IllegalAccessError::new);

        for(ObserveHashTagParams p : observeHashTagParams) {
            ObserveHashTag observeHashTag = new ObserveHashTag();
            HashTag hashTag = hashTagRepository.findByHashTagName(p.getHashTagName());
            observeHashTag.setHashTagId(hashTag.getHashTagId());
            observeHashTag.setHashTagName(p.getHashTagName());
            observeHashTag.setObservation(observation);
            observeHashTag.setObservationId(observationId);
            observeHashTagRepository.save(observeHashTag);
        }

    }

    public void createObserveFees(Long observationId, List<ObserveFee> feeList) {
        Observation observation = observationRepository.findById(observationId).orElseThrow(IllegalAccessError::new);
        for (ObserveFee p : feeList) {
            observation.getObserveFees().add(p);
        }
    }

    public Observation getObservation(Long observationId){
        Observation observation = observationRepository.findById(observationId).orElseThrow(IllegalAccessError::new);
        return observation;
    }
//test

    public List<SearchParams1> getObservationWithFilter(Filter filter, String searchKey) {
        List<Long> areaCodeList = filter.getAreaCodeList();
        List<Long> hashTagIdList= filter.getHashTagIdList();    //?????? ???????????? ?????????

        List<SearchParams1> resultParams = new ArrayList<>();   //???????????? param ?????????
        List<Long> hashtagResult = new ArrayList<>();   //???????????? ??????
        List<Long> filterIdList = new ArrayList<>();    //????????????(????????????, ?????? ??????)id ?????????
        List<Observation> searchResult = new ArrayList<>(); //??????+????????? ?????? ?????????


        if (!hashTagIdList.isEmpty()) {
            for(Long hashTagId : hashTagIdList){
                List<ObserveHashTag> observeHashTags = observeHashTagRepository.findByHashTagId(hashTagId);
                for (ObserveHashTag observeHashTag : observeHashTags) {
                    Long observationId = observeHashTag.getObservationId();
                    if (!hashtagResult.contains(observationId)) { //????????? ?????? ??????
                        hashtagResult.add(observationId);
                    }
                }
            }
        }

        if (!areaCodeList.isEmpty()) {
            for (Long areaCode : areaCodeList) {
                List<Observation> observationList = observationRepository.findByAreaCode(areaCode);
                if (hashTagIdList.isEmpty()) {
                    //???????????? ????????? ???????????? ????????????
                    for (Observation observation : observationList) {
                        filterIdList.add(observation.getObservationId());
                    }
                } else {
                    //???????????? ????????? ?????? ??????
                    for (Observation observation : observationList) {
                        Long observationId = observation.getObservationId();
                        if (hashtagResult.contains(observationId)) {
                            //???????????????????????? ?????? ????????? filter??????????????? ??????
                            filterIdList.add(observationId);
                        }
                    }
                }
            }
        } else {
            //area ???????????????
            filterIdList = hashtagResult;
        }

        if (searchKey != null) {
            List<Observation> keyResult = new ArrayList<>();    //???????????? ????????? ?????????
            searchResult = observationRepository.findByObservationNameContainingOrOutlineContaining(searchKey, searchKey);
            keyResult = observationRepository.findByObservationNameContainingOrOutlineContaining(searchKey, searchKey);

            if (!hashTagIdList.isEmpty() || !areaCodeList.isEmpty()) {
                //?????? ????????? ????????? ?????? ???????????? ??????, ????????? ?????? ??????
                for (Observation observation : keyResult) {
                    //?????? ????????? ?????? ?????????
                    if (!filterIdList.contains(observation.getObservationId())) {
                        //??????????????? ????????? ?????? ????????? ??????+??????????????????????????? ??????
                        searchResult.remove(observation);
                    }
                }
            }
        } else {
            for(Long p : filterIdList)
                searchResult.add(getObservation(p));
        }


        //?????? param??? ??????
        for(Observation observation : searchResult){

            if(observation.getObservationId()==999)
                continue;
            SearchParams1 searchParams1 = new SearchParams1();
            searchParams1.setItemId(observation.getObservationId());
            searchParams1.setTitle(observation.getObservationName());
            //????????? ??????????????? ??????
            String address = observation.getAddress();
            int i = address.indexOf(' ');
            if (i != -1){
                int j = address.indexOf(' ', i+1);
                if(j != -1){
                    searchParams1.setAddress(observation.getAddress().substring(0, j));
                } else{
                    searchParams1.setAddress(observation.getAddress());
                }
            } else{
                searchParams1.setAddress(observation.getAddress());
            }
//            searchParams1.setAddress(observation.getAddress());
            searchParams1.setLatitude(observation.getLatitude());
            searchParams1.setLongitude(observation.getLongitude());
            searchParams1.setIntro(observation.getIntro());
            searchParams1.setContentType(observation.getObserveType());
            searchParams1.setLight(observation.getLight());
            if (!observeImageRepository.findByObservationId(observation.getObservationId()).isEmpty()) {
                ObserveImage observeImage = observeImageRepository.findByObservationId(observation.getObservationId()).get(0);
                searchParams1.setThumbnail(observeImage.getImage());
            } else {
                searchParams1.setThumbnail(null);
            }
            List<ObserveHashTag> hashTagList = observeHashTagRepository.findByObservationId(observation.getObservationId());
            List<String> hashTagNames = new ArrayList<>();
            int k = 0;
            for (ObserveHashTag hashTag : hashTagList){
                if(k>2)
                    break;
                hashTagNames.add(hashTag.getHashTagName());
                k++;
            }
            searchParams1.setHashTagNames(hashTagNames);

            resultParams.add(searchParams1);
        }
        return resultParams;
    }

}

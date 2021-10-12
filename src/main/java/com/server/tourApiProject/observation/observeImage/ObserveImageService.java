package com.server.tourApiProject.observation.observeImage;

import com.server.tourApiProject.observation.ObservationRepository;
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
public class ObserveImageService {
    private final ObserveImageRepository observeImageRepository;
    private final ObservationRepository observationRepository;

    public List<String> getObserveImage(Long observationId) {
        List<String> observeImagePathList =new ArrayList<>();
        List<ObserveImage> observeImageList = observeImageRepository.findByObservationId(observationId);
        for(ObserveImage p : observeImageList) {
            observeImagePathList.add(p.getImage());
        }
        return observeImagePathList;
    }

    public List<ObserveImageParams2> getObserveImageInfo(Long observationId) {
        List<ObserveImageParams2> observeImageInfos =new ArrayList<>();
        List<ObserveImage> observeImageList = observeImageRepository.findByObservationId(observationId);
        for(ObserveImage p : observeImageList) {
            ObserveImageParams2 info = new ObserveImageParams2();
            info.setImage(p.getImage());
            info.setImageSource(p.getImageSource());
            observeImageInfos.add(info);
        }
        return observeImageInfos;
    }



}

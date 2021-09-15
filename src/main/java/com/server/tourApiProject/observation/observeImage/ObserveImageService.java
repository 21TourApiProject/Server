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



}

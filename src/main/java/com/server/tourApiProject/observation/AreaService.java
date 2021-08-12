package com.server.tourApiProject.observation;

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
public class AreaService {

    private final ObservationRepository observationRepository;

    public void createArea(List<AreaParams> areaParams) {
        for (AreaParams areaParam : areaParams){
            Area area = new Area();
            area.setAreaCode(areaParam.getCode());
            area.setAreaName(areaParam.getName());
            observationRepository.save(area);
        }
    }

    public List<Long> getAreaCode() {
        List<Area> all = observationRepository.findAll();
        List<Long> result = new ArrayList<>();
        for(Area a : all){
            result.add(a.getAreaCode());
        }
        return result;
    }

    public void createSigungu(Long areaCode, List<SigunguParams> sigunguParams) {
        Area a = observationRepository.findByAreaCode(areaCode);

        for (SigunguParams sigunguParam : sigunguParams){
            Area area = new Area();
            area.setAreaCode(areaCode);
            area.setAreaName(a.getAreaName());
            area.setSigunguCode(sigunguParam.getCode());
            area.setSigunguName(sigunguParam.getName());
            observationRepository.save(area);
        }
    }
}

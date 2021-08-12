package com.server.tourApiProject.touristPoint.area;

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

    private final AreaRepository touristPointRepository;

    public void createArea(List<AreaParams> areaParams) {
        for (AreaParams areaParam : areaParams){
            Area area = new Area();
            area.setAreaCode(areaParam.getCode());
            area.setAreaName(areaParam.getName());
            touristPointRepository.save(area);
        }
    }

    public List<Long> getAreaCode() {
        List<Area> all = touristPointRepository.findAll();
        List<Long> result = new ArrayList<>();
        for(Area a : all){
            result.add(a.getAreaCode());
        }
        return result;
    }

    public void createSigungu(Long areaCode, String areaName, List<SigunguParams> sigunguParams) {
        for (SigunguParams sigunguParam : sigunguParams){
            Area area = new Area();
            area.setAreaCode(areaCode);
            area.setAreaName(areaName);
            area.setSigunguCode(sigunguParam.getCode());
            area.setSigunguName(sigunguParam.getName());
            touristPointRepository.save(area);
        }
    }
}

package com.server.tourApiProject.touristPoint.area;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;

    public void createArea(AreaParams areaParams) {
        Area area = new Area();
        area.setAreaCode(areaParams.getCode1());
        area.setAreaName(areaParams.getName1());
        area.setSigunguCode(areaParams.getCode2());
        area.setSigunguName(areaParams.getName2());
        areaRepository.save(area);
    }
}

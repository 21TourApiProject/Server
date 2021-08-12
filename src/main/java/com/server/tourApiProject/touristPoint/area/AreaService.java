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

    public void createArea(AreaParams2 areaParams2) {
        Area area = new Area();
        area.setAreaCode(areaParams2.getCode1());
        area.setAreaName(areaParams2.getName1());
        area.setSigunguCode(areaParams2.getCode2());
        area.setSigunguName(areaParams2.getName2());
        areaRepository.save(area);
    }
}

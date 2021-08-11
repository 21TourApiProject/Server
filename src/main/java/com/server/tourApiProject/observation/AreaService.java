package com.server.tourApiProject.observation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;

    public void createArea(List<AreaParams> areaParams) {
        for (AreaParams areaParam : areaParams){
            Area area = new Area();
            area.setAreaCode(areaParam.getCode());
            area.setAreaName(areaParam.getName());
            areaRepository.save(area);
        }
    }
}

package com.server.tourApiProject.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WtAreaService {
    private final WtAreaRepository wtAreaRepository;

    public WtAreaParams getAreaInfo(String cityName, String provName) {
        WtArea wtArea = wtAreaRepository.findByCityNameAndProvName(cityName, provName);

        if (wtArea.getCityName().equals(cityName) && wtArea.getProvName().equals(provName)) {
            WtAreaParams wtAreaParams = new WtAreaParams();
            wtAreaParams.setLatitude(wtArea.getLatitude());
            wtAreaParams.setLongitude(wtArea.getLongitude());
            wtAreaParams.setMinLightPol(wtArea.getMinLightPol());
            wtAreaParams.setMaxLightPol(wtArea.getMaxLightPol());
            return wtAreaParams;
        }
        return WtAreaParams.builder().build();
    }
}

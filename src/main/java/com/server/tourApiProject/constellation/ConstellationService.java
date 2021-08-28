package com.server.tourApiProject.constellation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ConstellationService {
    private final ConstellationRepository constellationRepository;

    public void createConstellation(Constellation constellation) {
        constellationRepository.save(constellation);
    }

    public List<ConstellationParams> getConstellation() {
        List<ConstellationParams> result = new ArrayList<>();
        List<Constellation> list = constellationRepository.findAll();

        return getConstellationParams(result, list);
    }

    private List<ConstellationParams> getConstellationParams(List<ConstellationParams> result, List<Constellation> list) {
        for (Constellation cl : list) {
            Constellation constellation = constellationRepository.findById(cl.getConstId()).orElseThrow(IllegalAccessError::new);

            ConstellationParams params = new ConstellationParams();
            params.setConstId(constellation.getConstId());
            params.setConstImage(constellation.getConstImage());
            params.setConstName(constellation.getConstName());
            result.add(params);
        }
        return result;
    }

    public List<ConstellationParams> getTodayConst() {
        // startDate, endDate 12-30 ~ 01-22 인 경우 어떻게 할건지 고려
        List<ConstellationParams> result = new ArrayList<>();
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd"));
        //String currentDate = LocalDate.of(2021,01,22).format(DateTimeFormatter.ofPattern("MM-dd"));
        List<Constellation> list = constellationRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(currentDate, currentDate);

        return getConstellationParams(result, list);
    }

    public Constellation getDetailConst(Long constId) {
        Constellation constellation = constellationRepository.findById(constId).orElseThrow(IllegalAccessError::new);
        return constellation;
    }
}


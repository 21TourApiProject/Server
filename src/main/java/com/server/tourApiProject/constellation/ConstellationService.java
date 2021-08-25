package com.server.tourApiProject.constellation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
        List<ConstellationParams> result = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        List<Constellation> list = constellationRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(currentDate, currentDate);

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

    public Constellation getDetailConst(Long constId) {
        Constellation constellation = constellationRepository.findById(constId).orElseThrow(IllegalAccessError::new);
        return constellation;
    }
}


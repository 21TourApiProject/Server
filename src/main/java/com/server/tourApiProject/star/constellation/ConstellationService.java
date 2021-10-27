package com.server.tourApiProject.star.constellation;

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

    public List<ConstellationParams2> getConstNames() {
        List<ConstellationParams2> result = new ArrayList<>();
        List<Constellation> list = constellationRepository.findAll();

        return getConstellationParams2(result, list);
    }

    private List<ConstellationParams> getConstellationParams(List<ConstellationParams> result, List<Constellation> list) {
        for (Constellation cl : list) {
            Constellation constellation = constellationRepository.findById(cl.getConstId()).orElseThrow(IllegalAccessError::new);

            ConstellationParams params = new ConstellationParams();
            params.setConstId(constellation.getConstId());
            params.setConstName(constellation.getConstName());
            params.setConstEng(constellation.getConstEng());
            result.add(params);
        }
        return result;
    }

    public List<ConstellationParams2> getConstellationParams2(List<ConstellationParams2> result, List<Constellation> list) {
        for (Constellation cl : list) {
            Constellation constellation = constellationRepository.findById(cl.getConstId()).orElseThrow(IllegalAccessError::new);

            ConstellationParams2 params2 = new ConstellationParams2();
            params2.setConstName(constellation.getConstName());
            result.add(params2);
        }
        return result;
    }

    public List<ConstellationParams> getTodayConst() {
        List<ConstellationParams> result = new ArrayList<>();
        List<Constellation> resultAll = new ArrayList<>();
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd"));

        List<Constellation> list1 = constellationRepository.findByStartDate1LessThanEqualAndEndDate1GreaterThanEqual(currentDate, currentDate);
        List<Constellation> list2 = constellationRepository.findByStartDate2LessThanEqualAndEndDate2GreaterThanEqual(currentDate, currentDate);

        resultAll.addAll(list1);
        resultAll.addAll(list2);

        return getConstellationParams(result, resultAll);
    }

    public List<ConstellationParams2> getTodayConstName() {
        List<ConstellationParams2> result = new ArrayList<>();
        List<Constellation> resultAll = new ArrayList<>();
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd"));

        List<Constellation> list1 = constellationRepository.findByStartDate1LessThanEqualAndEndDate1GreaterThanEqual(currentDate, currentDate);
        List<Constellation> list2 = constellationRepository.findByStartDate2LessThanEqualAndEndDate2GreaterThanEqual(currentDate, currentDate);

        resultAll.addAll(list1);
        resultAll.addAll(list2);

        return getConstellationParams2(result, resultAll);
    }

    public Constellation getDetailConst(String constName) {
        Constellation constellation = constellationRepository.findByConstName(constName);
        return constellation;
    }
}


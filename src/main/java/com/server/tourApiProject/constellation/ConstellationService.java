package com.server.tourApiProject.constellation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service

@Transactional
@RequiredArgsConstructor
public class ConstellationService {
    private final ConstellationRepository constellationRepository;

    public List<Constellation> getAllConstellation() {
        return constellationRepository.findAll();
    }

    public void createConstellation(Constellation constellation) {
        constellationRepository.save(constellation);
    }
}

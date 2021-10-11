package com.server.tourApiProject.star.Horoscope;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HoroscopeService {
    private final HoroscopeRepository horoscopeRepository;

    public Horoscope getHoroscope(Long horId) {
        return horoscopeRepository.findById(horId).orElseThrow(IllegalAccessError::new);
    }

    public List<Horoscope> getAllHoroscopes() {
        return horoscopeRepository.findAll();
    }
}

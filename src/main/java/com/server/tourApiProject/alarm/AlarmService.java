package com.server.tourApiProject.alarm;

import com.server.tourApiProject.hashTag.HashTag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    public List<Alarm> getAllAlarm() {
        return alarmRepository.findAll();
    }

    public void createAlarm(Alarm alarm) {
        alarmRepository.save(alarm);
    }
}

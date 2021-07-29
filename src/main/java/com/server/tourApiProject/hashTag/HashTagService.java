package com.server.tourApiProject.hashTag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HashTagService {
    private final HashTagRepository hashTagRepository;

    public List<HashTag> getAllHashTag() {
        return hashTagRepository.findAll();
    }
}

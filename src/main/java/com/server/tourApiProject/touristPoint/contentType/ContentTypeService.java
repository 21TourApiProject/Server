package com.server.tourApiProject.touristPoint.contentType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ContentTypeService {

    private final ContentTypeRepository contentTypeRepository;

    public void createContentType(ContentType contentType) {
        contentTypeRepository.save(contentType);
    }

}

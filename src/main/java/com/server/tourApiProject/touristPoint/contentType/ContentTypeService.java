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

    public void createContentType1(ContentTypeParams2 contentTypeParams2) {
        ContentType contentType = new ContentType();
        contentType.setContentType(12);
        contentType.setContentName("관광지");
        contentType.setCat1Code(contentTypeParams2.getCode1());
        contentType.setCat2Code(contentTypeParams2.getCode2());
        contentType.setCat3Code(contentTypeParams2.getCode3());
        contentType.setCat1Name(contentTypeParams2.getName1());
        contentType.setCat2Name(contentTypeParams2.getName2());
        contentType.setCat3Name(contentTypeParams2.getName3());
        contentTypeRepository.save(contentType);
    }

    public void createContentType2(ContentTypeParams2 contentTypeParams2) {
        ContentType contentType = new ContentType();
        contentType.setContentType(39);
        contentType.setContentName("음식");
        contentType.setCat1Code(contentTypeParams2.getCode1());
        contentType.setCat2Code(contentTypeParams2.getCode2());
        contentType.setCat3Code(contentTypeParams2.getCode3());
        contentType.setCat1Name(contentTypeParams2.getName1());
        contentType.setCat2Name(contentTypeParams2.getName2());
        contentType.setCat3Name(contentTypeParams2.getName3());
        contentTypeRepository.save(contentType);
    }
}

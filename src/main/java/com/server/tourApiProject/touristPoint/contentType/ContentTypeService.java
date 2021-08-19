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

    public void createContentType1(ContentTypeParams contentTypeParams) {
        ContentType contentType = new ContentType();
        contentType.setContentType(12);
        contentType.setContentName("관광지");
        contentType.setCat1Code(contentTypeParams.getCode1());
        contentType.setCat2Code(contentTypeParams.getCode2());
        contentType.setCat3Code(contentTypeParams.getCode3());
        contentType.setCat1Name(contentTypeParams.getName1());
        contentType.setCat2Name(contentTypeParams.getName2());
        contentType.setCat3Name(contentTypeParams.getName3());
        contentTypeRepository.save(contentType);
    }

    public void createContentType2(ContentTypeParams contentTypeParams) {
        ContentType contentType = new ContentType();
        contentType.setContentType(39);
        contentType.setContentName("음식");
        contentType.setCat1Code(contentTypeParams.getCode1());
        contentType.setCat2Code(contentTypeParams.getCode2());
        contentType.setCat3Code(contentTypeParams.getCode3());
        contentType.setCat1Name(contentTypeParams.getName1());
        contentType.setCat2Name(contentTypeParams.getName2());
        contentType.setCat3Name(contentTypeParams.getName3());
        contentTypeRepository.save(contentType);
    }

    public void deleteContentType() {
        contentTypeRepository.deleteAll();
    }
}

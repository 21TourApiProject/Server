package com.server.tourApiProject.searchFirst;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SearchFirstService {

    private final SearchFirstRepository searchFirstRepository;

    public List<SearchFirst> getSearchFirst(String typeName) {
        return searchFirstRepository.findByTypeName(typeName);
    }
}

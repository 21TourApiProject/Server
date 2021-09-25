package com.server.tourApiProject.search;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchKey {

    Filter filter;
    String keyword;
}

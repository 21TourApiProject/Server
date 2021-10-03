package com.server.tourApiProject.star.Horoscope;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoroscopeParams {
    private String horImage;
    private String horEngTitle;
    private String horKrTitle;
    private String horPeriod;
    private String horDesc;
}

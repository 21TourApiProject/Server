package com.server.tourApiProject.star.Horoscope;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

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

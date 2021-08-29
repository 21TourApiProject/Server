package com.server.tourApiProject;

import java.time.LocalDateTime;

public class test {
    public static void main(String[] args) {
        LocalDateTime date1 = LocalDateTime.parse("2021-08-29T23:44:00.884434");
        LocalDateTime date2 = LocalDateTime.parse("2021-08-29T23:44:03.439894");
        if (date1.isBefore(date2)) {
            System.out.println("Date1 is before Date2");
        }
    }
}

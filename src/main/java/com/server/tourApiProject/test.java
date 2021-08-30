package com.server.tourApiProject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class test {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now(); // 현재시간
        LocalDateTime oneDayAgo = now.minusDays(1);
        System.out.println(oneDayAgo.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        Long a = Long.parseLong(LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        System.out.println(a);
    }
}

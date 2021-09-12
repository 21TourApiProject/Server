package com.server.tourApiProject;

public class test {
    public static void main(String[] args){

        String a = "";
        System.out.println(a);
//        String overview = "예약 문의) <a title=\"새창 : 템플스테이 홈페이지로 이동\" href=\"https://www.templestay.com/\">https://www.templestay.com/</a> - 템플스테이 사무실 063-322-6162";
//        int i = overview.indexOf("<a title=");
//        int j = overview.indexOf("</a>");
//        if (i != -1 && j != -1) {
//            overview = overview.substring(0, i-1) + overview.substring(j+4);
//
//        }System.out.println("overview = " + overview);
//
//        String overview = "※ 영업시간 11:30 ~ 22:00 (브레이크타임 15:00 ~ 17:00) 얼큰하고 톡 쏘는 맛이 일";
//        overview = overview.replace("※ 식품의약품안전처 음식점 위생등급 : 매우 우수(2017년)", "");
//        overview = overview.replace("[중소벤처기업부 2018년도 '백년가게'로 선정]", "");
//        overview = overview.replace("[중소벤처기업부2018년도'백년가게'로선정]", "");
//        overview = overview.replace("[중소벤처기업부 2019년도 '백년가게'로 선정]", "");
//        overview = overview.replace("※ 식품의약품안전처 음식점 위생등급 : 매우 우수(2017년)", "");
//        overview = overview.replace("※ 점심 주문마감(LO) 14:30 / 저녁 주문마감(LO) 21:20", "");
//        overview = overview.replace("※ 영업시간 11:50 ~ 14:30 (화,목,토,일), 17:00 ~ 22:00 (매일)", "");
//
//        System.out.println("0 = " + overview);
//
//        int n = overview.indexOf("(정보제공자");
//        if (n != -1 && overview.length() < 20){
//            overview = null;
//        }
//
//        int m = overview.indexOf("※ 영업시간");
//        if (m != -1){
//            System.out.println("1 = " + overview.substring(0,m+20));
//
//            int m2 = overview.indexOf("0 (");
//            int m3 = overview.indexOf("0(");
//            int m4 = overview.indexOf(")");
//
//            if(m2 != -1 && m4 != -1){
//                System.out.println("2 = " + overview.substring(m2+2,m4+1));
//                //overview = overview.substring(m4+1) + " " + overview.substring(0,m+20) + " " + overview.substring(m2+2,m4+1);
//                overview = overview.substring(0, m) + overview.substring(m4+1);
//            } else if(m3 != -1 && m4 != -1) {
//                System.out.println("2 = " + overview.substring(m3+1,m4+1));
//                //overview = overview.substring(m4+1) + " " + overview.substring(0,m+20) + " " +overview.substring(m3+1,m4+1);
//                overview = overview.substring(0, m) + overview.substring(m4+1);
//            } else{
//                //overview = overview.substring(m+20) + " " + overview.substring(0,m+20);
//                overview = overview.substring(0, m) + overview.substring(m+20);
//            }
//        }
//        while (overview.charAt(0) == ' '){
//            overview=overview.substring(1);
//        }
//        System.out.println("fin = " + overview);


//        String overview = "<b>※ 공지사항내용 : 2021년 3월 5일 대웅전 화재로 인한 일반인의 사찰 출입 통제 중</b> 아름다운 내장산국립공원의 품안에 안겨 있는내장사는 백제 무왕37년(63";
//        int n = overview.indexOf("<b>※");
//        int n2 = overview.indexOf("</b>");
//        if (n != -1 && n2 != -1){
//            System.out.println("mid = " + overview.substring(n+3, n2));
//            overview = overview.substring(0,n) + overview.substring(n2+4) + " " + overview.substring(n+3,n2);
//        }
//        System.out.println("b = " + overview);
//
//        while (overview.charAt(0) == ' '){
//            overview=overview.substring(1);
//        }
//        System.out.println("fin = " + overview);
    }
    public static class A{
        String a;
        String b;

        public A(String a, String b) {
            this.a = a;
            this.b = b;
        }

        public String getA() {
            return a;
        }

        public String getB() {
            return b;
        }

        public void setA(String a) {
            this.a = a;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}

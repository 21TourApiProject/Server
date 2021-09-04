package com.server.tourApiProject;

public class test {
    public static void main(String[] args) {
        String a = "※ 영업시간 10:00 ~ 20:30  미식가들이";
        int m = a.indexOf("※ 영업시간");
        int m2 = a.indexOf("(브레이크타임");
        if (m != -1){
            System.out.println("a =" + a.substring(m+20));
            if(m2 != -1){
                System.out.println("a =" + a.substring(m+20) + a.substring(m2));
            }
        }

        String b = "<b>※ 코로나바이러스감염증-19 공지사항※ 내용 : 물놀이장 임시휴관(2020.07.17. ~ 별도 공지 시까지)</b> 신라시대 보조선사가 창건";
        String b2 = "<b>※코로나바이러스감염증-19 공지사항※ 내용 : 물놀이장 임시휴관(2020.07.17. ~ 별도 공지 시까지)</b> 신라시대 보조선사가 창건";
        String c = "다는 굴이 보인다.<b>※ 조수간만의 영향과 기상악화로 인한 안전문제로 출입통제가 될 수 있으니, 관람당일 입장 통제시간 미리 확인 필요(064-794-2940)</b>";
        int n = b.indexOf("<b>※");
        int n2 = b.indexOf("</b>");
        String front;
        if (n != -1 && n2 != -1){
            if (b.indexOf("<b>※ ") != -1){
                front = b.substring(n+3,n2);
            } else{
                front = b.substring(n+2,n2);
            }

            String back = b.substring(n2+4);
            System.out.println("b =" + back+" "+front);
        }
    }
}

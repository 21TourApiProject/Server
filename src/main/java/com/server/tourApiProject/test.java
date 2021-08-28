package com.server.tourApiProject;

public class test {
    public static void main(String[] args) {
        String overview = "as<a href=das</a>d";
        int i = overview.indexOf("<a href=");
        int j = overview.indexOf("</a>");
        if (i != -1 && j != -1) {
            overview = overview.substring(0, i) + overview.substring(j);
            overview = overview.replaceAll("</a>", " ");
        }
        System.out.println(overview);
    }
}

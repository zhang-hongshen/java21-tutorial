package com.zhanghongshen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringNewAPI {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("init string");
        sb.repeat("one", 5);
        System.out.println(sb);

        String str = "one new one s";
        System.out.println("index of char o is " + str.indexOf('o', 4, 10));
        System.out.println("Is 😂 a emoji ? " + Character.isEmoji("😂".codePointAt(0)));


        // named group
        String line = "1;New York;8 336 817";
        String indexGroup = "index";
        String cityGroup = "city";
        String populationGroup = "population";
        Pattern pattern = Pattern.compile("""
                        (?<index>\\d+);\
                        (?<city>[ a-zA-Z]+);\
                        (?<population>[ \\d]+)$""");
        Matcher matcher = pattern.matcher(line);
        if(matcher.matches()){
            System.out.println("index " + matcher.group(indexGroup));
            System.out.println("city " + matcher.group(cityGroup));
            System.out.println("population " + matcher.group(populationGroup));
        }
    }
}

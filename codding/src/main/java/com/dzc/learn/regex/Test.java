package com.dzc.learn.regex;



import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        Pattern compile = Pattern.compile("[a-z]+_[a-z]+[ =]");
        String target = "asset_prop == '7' && organ_flag = '0'";
        Matcher m = compile.matcher(target);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}

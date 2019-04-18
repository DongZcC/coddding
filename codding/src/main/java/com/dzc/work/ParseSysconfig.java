package com.dzc.work;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;


public class ParseSysconfig {


    private static HashSet<String> configNo = new HashSet<>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("d:/sysconfig.txt")))) {
            String line;
            while ((line = bf.readLine()) != null) {
                configNo.add(line);
            }
        }


        System.out.println(configNo);
        System.out.println(configNo.size());

    }
}

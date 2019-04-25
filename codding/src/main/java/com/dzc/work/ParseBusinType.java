package com.dzc.work;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ParseBusinType {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:/busin.txt")))) {
            String line;
            int no = 271000;
            int zno = 274000;
            int wno = 277000;
            while ((line = br.readLine()) != null) {
                String[] busins = line.split(" ");
                for (String busin : busins) {
                    if (StringUtils.isEmpty(busin))
                        continue;

                    System.out.println("|" + busin + "|" + (no++) + "|" + (zno++) + "|" + wno++ + "|");
                }
            }
        }
    }
}

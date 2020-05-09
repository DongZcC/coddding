package com.dzc.work;


import org.apache.commons.lang.CharSet;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.charset.Charset;

public class Test {


    public static void main(String[] args) throws Exception {
        FileOutputStream out = new FileOutputStream("text.txt");


        // 字符流输出.  控制只能控制 字符的多少.
        OutputStreamWriter ow = new OutputStreamWriter(out, "GBK");

        String z = StringUtils.rightPad("度困困", 80 - "度困困".length(), ' ');

        ow.write(z);
        // 字节流输出. 字节流输出 控制不了输出文件的编码格式 ?.
//        FileOutputStream o2 = new FileOutputStream("text-byte.txt");
//        String d = StringUtils.rightPad("杜坤", 80, ' ');
//        String d2 = StringUtils.rightPad("杜坤堃", 80, ' ');
//
//        byte[] gbks = d.getBytes("GBK");
//        o2.write(gbks, 0, 80);
//        o2.write("\n".getBytes());
//        o2.write(d2.getBytes("GBK"), 0, 80);
//        o2.flush();
        ow.close();

    }


}

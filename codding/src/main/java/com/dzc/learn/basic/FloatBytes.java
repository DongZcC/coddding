package com.dzc.learn.basic;

import org.apache.commons.lang3.StringUtils;

public class FloatBytes {

    public String floaToString(float f) {
        String result = StringUtils.leftPad(Integer.toBinaryString(Float.floatToIntBits(f)), 32, '0');
        StringBuilder sb = new StringBuilder();
        sb.append(result.substring(0, 1));
        sb.append("-");
        sb.append(result.substring(1, 9));
        sb.append("-");
        sb.append(result.substring(9));
        return sb.toString();
    }

    public static void main(String[] args) {
        float data = .1f;
        FloatBytes floatBytes = new FloatBytes();
        System.out.println(floatBytes.floaToString(data));
    }
}

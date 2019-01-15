package com.dzc.learn.letcode;

public class AddBinary {

    // 两个二进制的数 做加法 考察的是简单的进位？
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        boolean carryFlag = false;
        int lenA = a.length() - 1;
        int lenB = b.length() - 1;
        while (lenA >= 0 || lenB >= 0) {
            char tempA = '0';
            char tempB = '0';
            if (lenA >= 0)
                tempA = a.charAt(lenA);
            if (lenB >= 0)
                tempB = b.charAt(lenB);

            if (tempA + tempB == 96) {
                if (carryFlag) {
                    result.append('1');
                    carryFlag = false;
                } else
                    result.append('0');
            } else if (tempA + tempB == 97) {
                if (carryFlag) {
                    result.append('0');
                    carryFlag = true;
                } else
                    result.append('1');
            } else {
                if (carryFlag) {
                    result.append('1');
                } else {
                    result.append('0');
                }
                carryFlag = true;
            }
            lenA--;
            lenB--;
        }
        if (carryFlag)
            result.append('1');
        return result.reverse().toString();
    }


    public String addBinary2(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 && j >= 0) {
            int value = (a.charAt(i--) - '0') + (b.charAt(j--) - '0') + carry;
            sb.append(value % 2);
            carry = value / 2;
        }

        while (i >= 0) {
            int value = (a.charAt(i--) - '0') + carry;
            sb.append(value % 2);
            carry = value / 2;
        }

        while (j >= 0) {
            int value = (b.charAt(j--) - '0') + carry;
            sb.append(value % 2);
            carry = value / 2;
        }

        if (carry > 0) {
            sb.append(1);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        addBinary.addBinary("1111", "1111");
    }
}

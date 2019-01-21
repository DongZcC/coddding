package com.dzc.learn.letcode;

import java.util.LinkedList;

public class SimplifyPath {

    public String simplifyPath(String path) {
        LinkedList<String> stack = new LinkedList<>();
        for (String s : path.split("/")) {
            if (s != null && !s.isEmpty()) {
                if ("..".equals(s)) {
                    if (!stack.isEmpty())
                        stack.pop();
                }
                else if (".".equals(s)) {

                } else
                    stack.push(s);
            }
        }
        StringBuilder sb = new StringBuilder("/");
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
            sb.append("/");
        }

        return sb.length() == 1 ? sb.toString() : sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) {
        SimplifyPath sp = new SimplifyPath();
        System.out.println(sp.simplifyPath("/../"));
    }
}

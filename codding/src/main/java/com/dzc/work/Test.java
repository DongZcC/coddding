package com.dzc.work;


import javax.script.ScriptException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    private static final Pattern PATTERN = Pattern.compile("%[^\\*]");


    public static void main(String[] args) throws ScriptException {

        // ScriptEngine engine= new ScriptEngineManager().getEngineByName("JavaScript");
        //引擎名称传入JavaScript、js、javascript、nashorn、Nashorn 均可等价
        //最好指定具体的引擎名称为nashorn。若指定为JavaScript 也是采用JDK8中默认js引擎nashorn
        //  ScriptEngine engine= new ScriptEngineManager().getEngineByName("nashorn");
//        ScriptEngine engine = new ScriptEngineManager().getEngineByName("Nashorn");
//
//        String remark = "age >= 18 && age <= 60";
//
//        String age = "50";
//
//        remark = remark.replace("age", age);
//
//        boolean result = (boolean) engine.eval(remark);
//        System.out.println(result);

//        Calendar d = Calendar.getInstance();
//        d.set(2099, 1, 1);
//        Date time = d.getTime();
//        System.out.println(time);
//        String condition = "organ_flag == '1' && exchange_type == '0'";
//        String condition2 = "'1' == organ_flag";
//
//        Matcher matcher = PATTERN.matcher(condition);
//        while (matcher.find()) {
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//        }


        Matcher matcher = PATTERN.matcher("%*111");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
     }

    private static void test2(Map<String, String> map) {
        map.put("2", "2");
        map.put("1", "3");
    }

    private static void test(List test) {
        test.add("3");
    }
}

package com.dzc.learn.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.*;

public class ParseExcel {

    private static Map<String, String> fieldMap = new HashMap<>();

    private static Set<String> businTypes = new HashSet<>();

    private static List<Service> services = new ArrayList<>();

    private static List<Integer> lineToMerge1 = new ArrayList<>();

    private static List<Integer> lineToMerge2 = new ArrayList<>();

    private static int lineNum = 2;

    public static void main(String[] args) throws Exception {

        // 1. 解析标准字段与类型的对应关系
        SAXReader reader = new SAXReader();
        Document doc = reader.read(ParseExcel.class.getResourceAsStream("/stdfields.xml"));
        Element root = doc.getRootElement();
        List<Element> elements = root.elements();
        elements.forEach(e -> fieldMap.put(e.attributeValue("name"), e.attributeValue("type")));

        // 2. 配置要处理的业务
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(ParseExcel.class.getResourceAsStream("/todoBusinType.txt")))) {
            String line;
            while ((line = bf.readLine()) != null) {
                businTypes.add(StringUtils.trim(line));
            }
        }

        // 3. 读取excel
        try (InputStream inputStream = ParseExcel.class.getResourceAsStream("/excel.xls")) {
            ExcelListener listener = new ExcelListener();
            EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1), listener);
        }

        // 4. 写出excel
        try (OutputStream out = new FileOutputStream("test.xlsx")) {
            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            Sheet sheet = new Sheet(1, lineNum);
            setColumnWidth(sheet);
            writer.write0(createOutput(writer), sheet);
            lineToMerge1.forEach(l -> writer.merge(l - 1, l - 1, 4, 6));
            lineToMerge2.forEach(l -> writer.merge(l - 1, l - 1, 2, 6));
            writer.finish();
        }
    }

    private static void setColumnWidth(Sheet sheet) {
        Map<Integer, Integer> columnWidth = new HashMap<>();
        columnWidth.put(0, 1150);
        columnWidth.put(1, 3000);
        columnWidth.put(2, 8700);
        columnWidth.put(3, 5000);
        columnWidth.put(4, 3500);
        columnWidth.put(5, 3000);
        columnWidth.put(6, 4600);
        sheet.setColumnWidthMap(columnWidth);
    }

    private static List<List<String>> createOutput(ExcelWriter writer) {
        List<List<String>> res = new ArrayList<>();

        services.forEach(service -> {
            // row1
            List<String> r1 = new ArrayList<>();
            r1.add(null);
            r1.add("功能名称");
            r1.add(service.getEnglishName() + lineNum);
            r1.add("版本号");
            r1.add(service.getVersion());
            r1.add("更新日期");
            r1.add(service.getDate());
            lineNum++;
            res.add(r1);


            // row2
            List<String> r2 = new ArrayList<>();
            r2.add(null);
            r2.add("功能中文");
            r2.add(service.getName());
            r2.add("接口标志");
            r2.add(service.getFlag());
            r2.add("结果集返回");
            r2.add(service.isResultSet() ? "是" : "否");
            lineNum++;
            res.add(r2);

            // row3
            List<String> r3 = new ArrayList<>();
            r3.add(null);
            r3.add("需求说明");
            r3.add(null);
            r3.add("备注");
            r3.add(null);
            r3.add(null);
            r3.add(null);
            lineToMerge1.add(lineNum);
            lineNum++;
            res.add(r3);

            // row4
            List<String> r4 = new ArrayList<>();
            r4.add(null);
            r4.add("功能描述");
            r4.add(service.getDescription());
            lineToMerge2.add(lineNum);
            lineNum++;
            res.add(r4);

            // row5
            List<String> r5 = new ArrayList<>();
            r5.add(null);
            r5.add("输入参数");
            r5.add("参数名");
            r5.add("类型");
            r5.add("说明");
            r5.add("必须");
            r5.add("缺省值");
            lineNum++;
            res.add(r5);


            service.getInput().forEach(p -> {
                List<String> row = new ArrayList<>();
                row.add(null);
                row.add(null);
                row.add(p.getName());
                row.add(p.getType());
                row.add(p.getDescription());
                row.add(p.isNecessary() ? "Y" : "N");
                row.add(p.getDefaultValue());
                lineNum++;
                res.add(row);
            });

            // row6
            List<String> r6 = new ArrayList<>();
            r6.add(null);
            r6.add("输出参数");
            r6.add("参数名");
            r6.add("类型");
            r6.add("说明");
            r6.add("必须");
            r6.add("缺省值");
            lineNum++;
            res.add(r6);

            service.getOutput().forEach(p -> {
                List<String> row = new ArrayList<>();
                row.add(null);
                row.add(null);
                row.add(p.getName());
                row.add(p.getType());
                row.add(p.getDescription());
                row.add(null);
                row.add(null);
                lineNum++;
                res.add(row);
            });

            // row7
            List<String> r7 = new ArrayList<>();
            r7.add(null);
            r7.add("业务说明");
            lineToMerge2.add(lineNum);
            lineNum++;
            res.add(r7);


            // row8
            List<String> r8 = new ArrayList<>();
            lineToMerge2.add(lineNum);
            lineNum++;
            res.add(r8);

            // row9
            List<String> r9 = new ArrayList<>();
            r9.add(null);
            r9.add("出错说明");
            r9.add("错误号");
            r9.add("错误信息");
            r9.add("错误说明");
            r9.add("错误级别");
            r9.add(null);
            lineNum++;
            res.add(r9);

            lineNum++;
            lineNum++;
            lineNum++;
            res.add(new ArrayList<>());
            res.add(new ArrayList<>());
            res.add(new ArrayList<>());

        });

        return res;
    }

    static class ExcelListener extends AnalysisEventListener {


        List<List<String>> row;

        List<List<List<String>>> todoList = new ArrayList<>();

        @Override
        public void invoke(Object object, AnalysisContext context) {
            List<String> data = (List<String>) object;
            divideGroup(data);
        }

        /**
         * if have something to do after all analysis
         *
         * @param context
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            todoList.stream()
                    .filter(s -> businTypes.contains(s.get(0).get(1)))
                    .forEach(s -> {
                        // 构造Service对象
                        Service service = new Service();
                        List<String> head = s.get(0);
                        List<String> desc = s.get(1);

                        service.setName(head.get(1));
                        service.setResultSet("Y".equals(head.get(head.size() - 2)));
                        service.setDescription(desc.get(1));

                        // 解析输入参数
                        for (List<String> e : s) {
                            if (StringUtils.equals("输入参数", e.get(0))) {
                                row = new ArrayList<>();
                            } else if (StringUtils.equals("输出参数", e.get(0))) {
                                service.setInput(processParam(row));
                            } else {
                                row.add(e);
                            }
                        }

                        // 解析输出参数
                        for (List<String> e : s) {
                            if (StringUtils.equals("输出参数", e.get(0))) {
                                row = new ArrayList<>();
                            } else if (StringUtils.equals("业务说明", e.get(0))) {
                                service.setOutput(processParam(row));
                            } else {
                                row.add(e);
                            }
                        }
                        services.add(service);
                    });


            List<String> keys = new ArrayList<>(businTypes);
            services.forEach(v -> keys.remove(v.getName()));
            System.out.println("未读取的服务列表： " + keys);
        }

        private List<Param> processParam(List<List<String>> params) {
            List<Param> res = new ArrayList<>();
            params.forEach(p -> {
                Param param = new Param();
                param.setNecessary("Y".equals(p.get(0)));
                param.setName(p.get(1));
                param.setType(fieldMap.get(param.getName()));
                param.setDescription(p.get(3));
                param.setDefaultValue(p.get(p.size() - 1));
                res.add(param);
            });
            return res;
        }


        private void divideGroup(List<String> data) {
            // 代表一个服务开始
            if (StringUtils.equals(data.get(0), "服务名")) {
                row = new ArrayList<>();
                row.add(data);
            }
            // 代表一个服务结束
            else if (StringUtils.equals(data.get(0), "业务说明")) {
                row.add(data);
                todoList.add(new ArrayList<>(row));
            } else {
                row.add(data);
            }
        }
    }


    /**
     * 定义服务对象
     */
    @Data
    static class Service {

        private boolean resultSet;

        private String description;

        private String englishName = "CMS";

        private String name;

        private String version = "V9.0.0.0";

        private String flag = "A";

        private String date = "2019/01/01";

        private List<Param> input;

        private List<Param> output;
    }


    /**
     * 参数对象
     */
    @Data
    static class Param {

        private String name;

        private String type;

        private String description;

        private boolean necessary;

        private String defaultValue;
    }

}

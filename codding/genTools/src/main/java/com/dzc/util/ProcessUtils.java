package com.dzc.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.dzc.process.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020-05-09 15:01
 */
public class ProcessUtils {

    public static MdbTable mdbTable;

    public static Map<String, StdField> stdFieldMap;

    public static String stdPath;

    public static String describePath;

    public static String outputDir;


    public static void parseStdField(File file) throws Exception {
        stdFieldMap = new HashMap<>();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(file);
        Element root = doc.getRootElement();
        List<Element> items = root.elements("items");
        for (Element item : items) {
            StdField field = StdField.builder()
                    .chineseName(item.attributeValue("chineseName"))
                    .dataType(item.attributeValue("dataType"))
                    .name(item.attributeValue("name")).build();

            stdFieldMap.put(field.getChineseName(), field);
        }
    }


    public static void processMdbFile(List<ExcelData> dataList) {

        MdbTable.MdbTableBuilder builder = MdbTable.builder();
        ExcelData excelData = dataList.get(0);
        builder.chineseName(excelData.getText2())
                .englishName(excelData.getText1());

        int index = -1;

        List<StdField> column = new ArrayList<>();

        for (int i = 1; i < dataList.size(); i++) {
            if ("(索引名)".equals(dataList.get(i).getText1())) {
                index = i;
                break;
            }

            // 标准字段.
            ExcelData d = dataList.get(i);
            StdField stdField = stdFieldMap.get(d.getText1());
            column.add(stdField);
        }
        builder.columns(column);

        List<MdbIndex> indexList = new ArrayList<>();
        for (int i = index + 1; i < dataList.size(); i++) {
            MdbIndex.MdbIndexBuilder b1 = MdbIndex.builder();
            ExcelData d = dataList.get(i);

            b1.indexName(d.getText1());
            List<StdField> ins = new ArrayList<>();
            for (String name : d.getText2().split("/")) {
                ins.add(stdFieldMap.get(name));
            }
            b1.fields(ins);
            indexList.add(b1.build());
        }
        builder.index(indexList);

        mdbTable = builder.build();
    }

    public static void createOutputFile(String outputDir) throws Exception {

        // 输出文件.

        OutputFormat format = new OutputFormat();
        format.setLineSeparator("\r\n");
        //设置是否缩进
        format.setIndent(true);
        //以四个空格方式实现缩进
        format.setIndent("    ");
        //设置是否换行
        format.setNewlines(true);

        String fileName = outputDir + File.separator + mdbTable.getEnglishName().replace("TV", "MV") + ".mdbtable";
        XMLWriter writer = new XMLWriter(new FileOutputStream(fileName), format);

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("mdbmodel:MdbTableResourceData");

        root.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.addNamespace("chouse", "http://www.hundsun.com/ares/studio/jres/database/chouse/1.0.0");
        root.addNamespace("mdbmodel", "http://www.hundsun.com/ares/studio/mdb/1.0.0");
        root.addAttribute("chineseName", mdbTable.getChineseName());

        Element data2 = root.addElement("data2");
        data2.addElement("value").addNamespace("type", "chouse:TableBaseProperty");

        root.addElement("histories").addAttribute("modifiedDate", "2018-03-06 16:59")
                .addAttribute("version", "1.0.0.1")
                .addAttribute("modifiedBy", "zhangyw")
                .addAttribute("charger", "zhangyw")
                .addAttribute("modified", "新建资源");


        for (StdField column : mdbTable.getColumns()) {
            root.addElement("columns")
                    .addAttribute("fieldName", column.getName())
                    .addAttribute("nullable", "false");
        }

        for (MdbIndex index : mdbTable.getIndex()) {
            Element indexes = root.addElement("indexes");
            indexes.addAttribute("name", index.getIndexName());

            for (StdField field : index.getFields()) {
                indexes.addElement("columns").addAttribute("columnName", field.getName()).addAttribute("ascending",
                        "true");
            }
        }

        writer.write(document);
        writer.close();
    }

    public static void readExcel(File describe) {
        ExcelReader reader = EasyExcel.read(describe)
                .head(ExcelData.class)
                .registerReadListener(new MdbDataListener())
                .build();
        ReadSheet sheet = EasyExcel.readSheet(0).build();
        reader.read(sheet);
    }
}

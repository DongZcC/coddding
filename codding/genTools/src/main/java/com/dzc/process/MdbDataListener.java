package com.dzc.process;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dzc.util.ProcessUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2020-05-09 15:30
 */
public class MdbDataListener extends AnalysisEventListener<ExcelData> {

    List<ExcelData> dataList = new ArrayList<>();

    @Override
    public void invoke(ExcelData excelData, AnalysisContext analysisContext) {
        dataList.add(excelData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        ProcessUtils.processMdbFile(dataList);
    }
}

package com.dzc.process;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 * @date 2020-05-09 15:19
 */
@Builder
@Data
public class MdbTable {

    private String englishName;

    private String chineseName;

    private List<MdbIndex> index;

    private List<StdField> columns;

}

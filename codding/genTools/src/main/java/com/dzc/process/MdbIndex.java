package com.dzc.process;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 * @date 2020-05-09 15:20
 */
@Data
@Builder
public class MdbIndex {

    private String indexName;

    private List<StdField> fields;
}

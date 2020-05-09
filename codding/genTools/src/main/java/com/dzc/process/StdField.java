package com.dzc.process;

import lombok.Builder;
import lombok.Data;

/**
 * @author Administrator
 * @date 2020-05-09 15:00
 */
@Data
@Builder
public class StdField {

    private String name;

    private String chineseName;

    private String dataType;

}

package com.xl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-20
 * @time 23:24
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnInfo {
    private String column_name;
    private String column_type;
    private String column_comment;
}

package com.xl.entity;

import java.util.List;
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
public class TableInfo {
    private String table_name;
    private String table_comment;
    private List<ColumnInfo> columnInfos;
    
    public TableInfo(String table_name, String table_comment) {
        this.table_name = table_name;
        this.table_comment = table_comment;
    }
}

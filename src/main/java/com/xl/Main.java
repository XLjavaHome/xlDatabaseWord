package com.xl;

import com.xl.entity.TableInfo;
import com.xl.service.FileService;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-19
 * @time 21:57
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        FileService fileService = context.getBean(FileService.class);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(
                "select\n" + "  a.table_name\n" + "     , a.table_comment\n" + "     , b.column_name\n" + "     , b.column_type\n"
                + "     , b.column_comment\n" + "from\n" + "  information_schema.tables a\n"
                + "  left join information_schema.columns b on a.table_name = b.table_name\n" + "where a.table_schema = ?\n"
                + "order by b.table_name, b.ordinal_position ", "xl");
        //key 表的名称，表    TableInfo 里放属性
        Map<String, TableInfo> tableInfoListMap = fileService.dealWithData(mapList);
        //List<Map<String, Object>> collect = mapList.stream().map(stringObjectMap -> ).collect(Collectors.toList());
        Collection<TableInfo> values = tableInfoListMap.values();
        fileService.generateWordDocument(values);
    }
}

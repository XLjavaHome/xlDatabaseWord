package com.xl.service;

import com.xl.entity.TableInfo;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-20
 * @time 23:28
 * To change this template use File | Settings | File Templates.
 */
public interface FileService {
    /**
     * 生成数据库字典
     *
     * @param columnInfoList
     */
    void generateWordDocument(Collection<TableInfo> columnInfoList);
    
    /**
     * 清洗数据库查询数据
     *
     * @param mapList
     * @return
     */
    Map<String, TableInfo> dealWithData(List<Map<String, Object>> mapList);
}

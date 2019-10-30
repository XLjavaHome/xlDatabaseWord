package com.xl;

import javax.sql.DataSource;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.xl.ImportTest;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-19
 * @time 22:02
 * To change this template use File | Settings | File Templates.
 */
@ComponentScan(basePackages = {"com.xl"})
@Import(ImportTest.class)
@Cacheable
public class AppConfig {
    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
    
    /**
     * springCache缓存
     *
     * @return
     */
    @Bean
    public CacheManager getSimpleCacheManager() {
        //用SimpleCacheManager报错。
        ConcurrentMapCacheManager simpleCacheManager = new org.springframework.cache.concurrent.ConcurrentMapCacheManager();
        return simpleCacheManager;
    }
}

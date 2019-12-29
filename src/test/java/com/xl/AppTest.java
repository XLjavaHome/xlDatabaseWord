package com.xl;

import java.util.Map;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-22
 * @time 16:28
 * To change this template use File | Settings | File Templates.
 */
public class AppTest {
    @Test
    public void hello() {
        Map map = new org.springframework.util.LinkedCaseInsensitiveMap(10);
        map.put("name", "hello");
        System.out.println(map.get("name"));
    }
}


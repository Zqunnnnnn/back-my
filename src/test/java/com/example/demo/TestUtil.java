package com.example.demo;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.bean.Emp;
import com.example.demo.bean.Wife;
import org.junit.jupiter.api.Test;

public class TestUtil {
    @Test
    public void test(){
        Wife wife = new Wife(1,"mary");
        String s = JSONUtil.toJsonStr(wife);
        JSON parse = JSONUtil.parse(s);
        Wife wife1 = parse.toBean(Wife.class);
        System.out.println(s);
        System.out.println(parse);

    }
}

package com.ivorita.test;

import com.ivorita.bean.RegUser;
import com.ivorita.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo {

    //1.创建JdbcTemplate对象
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Test
    public void test(){
        String sql = String.format("SELECT * FROM (SELECT * FROM GranaryInfo ORDER BY id DESC LIMIT %s) gi ORDER BY id", 10);
        List<Map<String, Object>> list = template.queryForList(sql);

        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
        
    }

    @Test
    public void test1(){
        String sql = "select * from reg_usr";
        List<RegUser> list = template.query(sql, new BeanPropertyRowMapper<RegUser>(RegUser.class));
        //iter快捷键
        for (RegUser regUser : list) {
            //sout快捷键
            System.out.println(regUser);
        }
    }


}

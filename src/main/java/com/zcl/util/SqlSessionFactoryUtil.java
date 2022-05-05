package com.zcl.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class SqlSessionFactoryUtil {
    /**
     * 提升全局作用域返回
     */
    private static SqlSessionFactory sqlSessionFactory;

    // 静态代码块会随着类的加载而自动执行，且只执行一次
    static {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 被调用方法放回
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }
}

package com.zcl.webs.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 请求分配路径
 */
public class DistributionOfPath extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        // 1、获取请求路径
        String uri = req.getRequestURI();
        // 2、获取最后一段路径，方法名称的索引
        int index = uri.lastIndexOf("/");
        // 3、截取索引开始位置+1
        String methodName = uri.substring(index + 1);
        try {
            // 4、获取字节码对象
            Class<? extends DistributionOfPath> cls = this.getClass();
            // 5、获取Method对象
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 6、执行方法
            method.invoke(this,req,resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

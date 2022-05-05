package com.zcl.webs.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 过滤器,是否有登录
 */
@WebFilter("/*")
public class LoginFilter implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 强转request
        HttpServletRequest req = (HttpServletRequest) request;
        // 通过数组存储跟登录有关的资源路径放行
        String[] urls = {"/css/","/image/","/js/","/index.html","/test/login","/element-ui/","axios/","/CheckCodeServlet"};
        // 获取当前访问路径
        String url = req.getRequestURL().toString();
        // 循环判断url是否包含urls
        for (String u : urls) {
            if(url.contains(u)){
                // 放行
                chain.doFilter(request, response);
                return;
            }
        }

        // 1、判断是否有Session
        Object teacher = req.getSession().getAttribute("teacher");
        // 判断teacher是否为null
        if(teacher != null){
            // 登录过了有session放行
            chain.doFilter(request, response);
        }else {
            // 没有登录，跳转登录页面，并提示
            /*response.setContentType("text/json;charset=utf-8");
            response.getWriter().write("您尚未登录");*/
            // 转发到登录页面
            req.getRequestDispatcher("/index.html").forward(req,response);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器....");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁...");
    }
}

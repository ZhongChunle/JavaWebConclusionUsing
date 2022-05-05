package com.zcl.webs.servlet;

import com.alibaba.fastjson.JSON;
import com.zcl.pojo.Teacher;
import com.zcl.service.imol.teacherServiceImpl;
import com.zcl.service.teacherService;
import com.zcl.util.CheckCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test/*")
public class TeacherServlet extends  DistributionOfPath{
    private teacherService teacherService = new teacherServiceImpl();

    /**
     * 教师登录方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("请求登录");
        // 接收页面数据
        // ------ post提交方式获取数据 -----
        BufferedReader reader = request.getReader();
        String str = reader.readLine();
        System.out.println("读取行的数据："+str);
        // 将获取的数据转换为Teacher实体类
        Teacher teacher1 = JSON.parseObject(str, Teacher.class);
        System.out.println("登录获取的数据："+teacher1);
        // 调用dao层方法，传递参数查询
        Teacher teacher = teacherService.queryTeacher(teacher1.getTeacherName(),teacher1.getPassword());
        // ----- get提交方式获取数据 -----
        /*String teacherName = request.getParameter("teacherName");
        String password = request.getParameter("password");
        Teacher teacher = teacherService.queryTeacher(teacherName,password);*/
        // 2、把对象转为JSON
        // 3、写数据
        response.setContentType("text/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        // System.out.println("登录查询结果："+teacher);
        // 4、判断查询的结果是否为空
        if(teacher != null){
            writer.write("succeed");
            //将登陆成功后的user对象，存储到session
            HttpSession session = request.getSession();
            session.setAttribute("teacher",teacher);
        }else {
            writer.write("登录失败");
        }
    }

    /**
     * 验证码生成
     */
    public void CheckCodeServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 字节输出流
        ServletOutputStream oup = response.getOutputStream();
        String s = CheckCodeUtil.outputVerifyImage(100, 50, oup, 4);
        // 存储到Session中
        HttpSession session = request.getSession();
        session.setAttribute("checkCodeGen",s);
        // 响应数据
        response.getWriter().write(s);
    }
}

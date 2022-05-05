package com.zcl.webs.servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zcl.pojo.PageStudent;
import com.zcl.pojo.Student;
import com.zcl.service.StudentService;
import com.zcl.service.imol.StudentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/stud/*")
public class StudentServlet extends DistributionOfPath{
    // 多态创建Service层代码
    private StudentService studentService = new StudentServiceImpl();

    /**
     * 获取分页数据进行分页查询
     * @param request 接受参数
     * @param response 响应参数
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        // 1、获取当前分页的页码和每页展示的条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        // 转换整形数据
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        // 获取查询条件对象【post提交】
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        // 转换brand对象
        Student student = JSON.parseObject(params, Student.class);
        // 2、调用查询
        PageStudent<Student> pageStudent = studentService.selectByPageAndCondition(currentPage, pageSize, student);
        // 3、将查询的结果进行json转换响应给页面
        String jsonString = JSON.toJSONString(pageStudent);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 获取前端也页面添加学生信息
     * @param request
     * @param response
     */
    public void addStudent(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        // 接收前端传递的数据
        BufferedReader bufferedReader = request.getReader();
        // 读取一行数据
        String student = bufferedReader.readLine();
        // 对接收的数据进行json转换
        Student studData = JSON.parseObject(student, Student.class);
        // 调用service服务层代码
        studentService.addStudent(studData);
        // 将数据返回页面
        response.getWriter().write("success");
    }

    /**
     * 根据id删除学生数据
     * @param request 接收参数
     * @param response 响应参数
     */
    public void delStudent(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取请求的值
        String id = request.getParameter("id");
        int studId = Integer.parseInt(id);
        System.out.println("接收页面的id："+studId);
        // 调用service层的删除方法
        studentService.delStudent(studId);
        // 返回响应数据
        response.getWriter().write("success");
    }

    /**
     * 根据id数组删除学生数据
     * @param request
     * @param response
     */
    public void delStudentAll(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取页面传递的数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        // 转换成数组的json
        int[] ids = JSON.parseObject(params, int[].class);
        // 调用删除方法
        studentService.delStudentAll(ids);
        // 响应返回的数据
        response.getWriter().write("success");
    }

    /**
     * 根据学生id回填数据信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectStudentId(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取前端页面的查询id数据
        int id = Integer.parseInt(request.getParameter("id"));
        // 调用执行service层的方法
        Student student = studentService.selectDataId(id);
        // 转换成json对象响应给前端请求
        String toJSONString = JSON.toJSONString(student);
        System.out.println("查询出来的数据："+toJSONString);
        // 响应格式
        response.setContentType("text/json;charset=utf-8");
        // 响应前端页面
        response.getWriter().write(toJSONString);
    }

    /**
     * 根据id修改学生数据
     * @param request
     * @param response
     */
    public void updataStudent(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 格式转换
        request.setCharacterEncoding("utf-8");
        // 接收前端的id
        BufferedReader reader = request.getReader();
        String student = reader.readLine();
        // 转换层json格式
        Student studentJson = JSON.parseObject(student, Student.class);
        System.out.println("接收的修改数据："+studentJson);
        // 调用方法
        studentService.upDataStudent(studentJson);
        // 响应请求数据
        response.getWriter().write("success");
    }
}

package com.zcl.service;

import com.zcl.pojo.Teacher;

public interface teacherService {
    /**
     * 查询教师登录接口方法
     * @param studentName
     * @param password
     * @return
     */
    Teacher queryTeacher(String studentName, String password);
}

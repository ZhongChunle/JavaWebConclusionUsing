package com.zcl.service;

import com.zcl.pojo.PageStudent;
import com.zcl.pojo.Student;

public interface StudentService {
    /**
     * 分页条件查询数据返回
     * @param currentPage 分页开始位置
     * @param pagesize 查询数据条数
     * @param student 查询条件
     * @return
     */
    PageStudent<Student> selectByPageAndCondition(int currentPage, int pagesize, Student student);

    /**
     * 新增学生数据
     * @param student 学业上数据实体类
     */
    void addStudent(Student student);

    /**
     * 根据id删除学生数据
     * @param id 学生id
     */
    void delStudent(int id);

    /**
     * 根据id数组删除所有学生数据
     * @param ids 学生id数组
     */
    void delStudentAll(int[] ids);

    /**
     * 根据id查询数据回填对话框
     * @param id 学生id
     * @return
     */
    Student selectDataId(int id);

    /**
     * 修改数据学生数据
     * @param student
     */
    void upDataStudent(Student student);
}

package com.zcl.mapper;

import com.zcl.pojo.Student;
import com.zcl.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface teacherMapper {

    /**
     * 老师登信息查询
     * @param studentName 登录名称
     * @param password 登录密码
     * @return
     */
    @Select("select * from tb_teacher where teacherName = #{studentName} and  password = #{password}")
    Teacher queryTeacher(@Param("studentName")String studentName, @Param("password")String password );
}

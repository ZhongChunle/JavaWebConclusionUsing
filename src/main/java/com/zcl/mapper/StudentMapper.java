package com.zcl.mapper;

import com.zcl.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {

    /**
     * 1、条件查询和分页查询
     * @param begin 开始分页位置
     * @param size 查询总数
     * @param student 条件查询
     * @return
     */
    List<Student> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("student") Student student);

    /**
     * 2、根据条件查询数据总数
     * @param student 查询条件
     * @return
     */
    int selectTotalCountByCondition(Student student);

    /**
     * 新增学生学习
     * @param student 学生学习实体类
     * @return
     */
    @Select("insert into tb_student(student_name, sex, class_name, idCode, phone, status) values (#{studentName},#{sex},#{className},#{idCode},#{phone},#{status})")
    void addStudent(Student student);

    /**
     * 根据id删除数据
     * @param id
     */
    @Delete("delete from tb_student where id = #{id}")
    void delStudent(int id);

    /**
     * 批量删除学生数据
     * @param ids
     */
    void delStudentAll(@Param("ids") int[] ids);

    /**
     * 根据id查询数据回填对话框
     * @param id
     * @return
     */
    @Select("select * from tb_student where id = #{id}")
    @ResultMap("studResultMap")
    Student selectDataId(int id);

    /**
     * 根据页面数据修改信息
     * @param student
     */
    @Update("update tb_student set student_name = #{studentName},sex = #{sex},class_name = #{className}," +
            "idCode = #{idCode},phone = #{phone},status = #{status} where id = #{id};")
    @ResultMap("studResultMap")
    void upDataStudent(Student student);
}

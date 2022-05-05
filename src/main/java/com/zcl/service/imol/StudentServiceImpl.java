package com.zcl.service.imol;

import com.zcl.mapper.StudentMapper;
import com.zcl.pojo.PageStudent;
import com.zcl.pojo.Student;
import com.zcl.service.StudentService;
import com.zcl.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    SqlSessionFactory sessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    /**
     * 分页数据查询
     * @param currentPage 分页开始位置
     * @param pagesize 查询数据条数
     * @param student 查询条件
     * @return
     */
    @Override
    public PageStudent<Student> selectByPageAndCondition(int currentPage, int pagesize, Student student) {
        // 1、获取sqlsession对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 2、获取mapper对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // 3、根据当前页面显示条数计算开始索引
        int begin = (currentPage - 1) * pagesize;
        // 4、查询的条目数
        int size = pagesize;
        // 5、处理模糊查询的%%
        String studentName = student.getStudentName();
        if(studentName != null && studentName.length() > 0){
            student.setStudentName("%" + studentName + "%");
        }
        String idCode = student.getIdCode();
        if(idCode != null && idCode.length() > 0){
            student.setIdCode("%"+idCode+"%");
        }
        String phone = student.getPhone();
        if(phone != null && phone.length() > 0){
            student.setPhone("%"+phone+"%");
        }
        // 6、调用查询分页方法
        List<Student> students = mapper.selectByPageAndCondition(begin, size, student);
        // 7、查询总记录条数
        int count = mapper.selectTotalCountByCondition(student);
        // 8、封装数据返回
        PageStudent<Student> pageStudent = new PageStudent<>();
        pageStudent.setRows(students);
        pageStudent.setTotalCiunt(count);
        return pageStudent;
    }

    /**
     * 继承新增学生接口
     * @param student 学业上数据实体类
     */
    @Override
    public void addStudent(Student student) {
        // 获取sql
        SqlSession sqlSession = sessionFactory.openSession();
        // 获取mapper
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // 获取执行dao层代码新增数据
        mapper.addStudent(student);
        // 事务提交{不写无法新增成功数据}
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    /**
     * 根据id删除学生数据
     * @param id 学生id
     */
    @Override
    public void delStudent(int id) {
        // 获取sql
        SqlSession sqlSession = sessionFactory.openSession();
        // 获取map
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // 调用dao层方法
        mapper.delStudent(id);
        // 提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    /**
     * 根据id数组删除所有学生数据
     * @param ids 学生id数组
     */
    @Override
    public void delStudentAll(int[] ids) {
        // 获取sql
        SqlSession sqlSession = sessionFactory.openSession();
        // 获取map
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // 调用执行方法
        mapper.delStudentAll(ids);
        // 提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    /**
     * 根据学生id查询学生信息回填对话框
     * @param id 学生id
     * @return
     */
    @Override
    public Student selectDataId(int id) {
        // 获取sql
        SqlSession sqlSession = sessionFactory.openSession();
        // 获取mapper
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // 调用方法查询数据
        Student student = mapper.selectDataId(id);
        return student;
    }

    /**
     * 根据id修改学生数据
     * @param student
     */
    @Override
    public void upDataStudent(Student student) {
        // 获取sql
        SqlSession sqlSession = sessionFactory.openSession();
        // 获取mapper
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // 调用方法
        mapper.upDataStudent(student);
        // 提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }
}

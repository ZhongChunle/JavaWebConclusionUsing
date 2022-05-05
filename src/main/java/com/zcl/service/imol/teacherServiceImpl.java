package com.zcl.service.imol;

import com.zcl.mapper.teacherMapper;
import com.zcl.pojo.Teacher;
import com.zcl.service.teacherService;
import com.zcl.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 教师登录方法
 */
public class teacherServiceImpl implements teacherService {
    /**
     * 获取SqlSessionFactory工厂对象
     */
    SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();

    /**
     * 教师登录信息查询业务逻辑
     * @param studentName 接收的登录名
     * @param password 接收的登录密码
     * @return
     */
    @Override
    public Teacher queryTeacher(String studentName, String password) {
        // 1、获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        // 2、获取mapper对象
        teacherMapper mapper = sqlSession.getMapper(teacherMapper.class);
        // 3、调用对应的dao层方法
        Teacher teacher = mapper.queryTeacher(studentName, password);
        // 4、释放资源
        sqlSession.close();
        // 5、返回查询结果
        return teacher;
    }
}

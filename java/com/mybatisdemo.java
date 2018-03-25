package com;

import com.dao.Istudent;
import com.entry.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.text.MessageFormat;
import java.util.List;

public class mybatisdemo {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("com/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SqlSession session = sqlSessionFactory.openSession();
        try {
            //sqlSessionFactory.getConfiguration().addMapper(IUser.class);
            //User user = (User) session.selectOne( "com.yiibai.mybatis.models.UserMapper.getUserByID", 1);

            // 用户数据列表
            getStudentList();
            // 插入数据
            // testInsert();

            // 更新用户
            testUpdate();

            // 删除数据
            //testDelete();

        } finally {
            session.close();
        }
    }

    //
    public static void testInsert()
    {
        try
        {
            // 获取Session连接
            SqlSession session = sqlSessionFactory.openSession();
            // 获取Mapper
            Istudent studentMapper = session.getMapper(Istudent.class);
            System.out.println("Test insert start...");
            // 执行插入
            Student student = new Student();
            student.setId(2);
            student.setName("Google");
            student.setDept("Tech");
            student.setPhone("120");
            studentMapper.insertStudent(student);
            // 提交事务
            session.commit();

            // 显示插入之后User信息
            System.out.println("After insert");
            getStudentList();
            System.out.println("Test insert finished...");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 获取用户列表
    public static void getStudentList() {
        try {
            SqlSession session = sqlSessionFactory.openSession();
            Istudent istudent = session.getMapper(Istudent.class);
            // 显示User信息
            System.out.println("Test Get start...");
            printUsers(istudent.getStudentList());
            System.out.println("Test Get finished...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testUpdate()
    {
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            Istudent istudent = session.getMapper(Istudent.class);
            System.out.println("Test update start...");
            printUsers(istudent.getStudentList());
            // 执行更新
            Student student = istudent.getStudentByID(1);
            student.setName("New name");
            istudent.updateStudent(student);
            // 提交事务
            session.commit();
            // 显示更新之后User信息
            System.out.println("After update");
            printUsers(istudent.getStudentList());
            System.out.println("Test update finished...");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 删除用户信息
    public static void testDelete()
    {
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            Istudent istudent = session.getMapper(Istudent.class);
            System.out.println("Test delete start...");
            // 显示删除之前User信息
            System.out.println("Before delete");
            printUsers(istudent.getStudentList());
            // 执行删除
            istudent.deleteStudent(2);
            // 提交事务
            session.commit();
            // 显示删除之后User信息
            System.out.println("After delete");
            printUsers(istudent.getStudentList());
            System.out.println("Test delete finished...");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * 打印用户信息到控制台
     *
     * @param students
     */
    private static void printUsers(final List<Student> students) {
        int count = 0;

        for (Student student : students) {
            System.out.println(MessageFormat.format(
                    "============= User[{0}]=================", ++count));
            System.out.println("User Id: " + student.getId());
            System.out.println("User Name: " + student.getName());
            System.out.println("User Dept: " + student.getDept());
        }
    }

}

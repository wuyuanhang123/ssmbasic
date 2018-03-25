package com.dao;

import com.entry.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//作为映射类，将原本需在xml中配置的映射变成了接口实现
public interface Istudent {
    public Student getStudentByID(int id);

    public void insertStudent(Student student);

    public void updateStudent(Student student);

    public void deleteStudent(int id);

    public List<Student> getStudentList();

}

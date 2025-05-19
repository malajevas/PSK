package com.example.mapper;

import java.util.List;

import com.example.model.Course;
import com.example.model.Student;
import com.example.model.University;

public interface StudentMapper {
    Student selectById(int id);
    List<Student> selectAll();
    void insert(Student student);
    void update(Student student);
    void deleteById(int id);
    List<Student> selectByUniversityId(int universityId);
    
    
    University selectUniversityById(int id);
    List<Course> selectCoursesByStudentId(int studentId);
}

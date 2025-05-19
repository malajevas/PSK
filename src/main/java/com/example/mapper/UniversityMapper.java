package com.example.mapper;

import java.util.List;

import com.example.model.Course;
import com.example.model.Student;
import com.example.model.University;

public interface UniversityMapper {
    University selectById(int id);
    List<University> selectAll();
    void insert(University university);
    void update(University university);
    void deleteById(int id);

    // Nested selects used in resultMap
    List<Student> selectStudentsByUniversityId(int universityId);
    List<Course> selectCoursesByUniversityId(int universityId);
}

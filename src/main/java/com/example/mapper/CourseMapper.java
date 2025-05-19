package com.example.mapper;

import java.util.List;

import com.example.model.Course;

public interface CourseMapper {
    Course selectById(int id);
    List<Course> selectAll();
    void insert(Course course);
    void update(Course course);
    void deleteById(int id);
}

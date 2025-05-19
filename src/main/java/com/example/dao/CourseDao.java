package com.example.dao;

import java.util.List;

import com.example.model.Course;

public interface CourseDao {
    List<Course> findAll();
    Course find(int id);
    void create(Course course);
    Course update(Course course);
    void delete(int id);
}

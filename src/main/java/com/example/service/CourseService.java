package com.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

import com.example.dao.CourseDao;
import com.example.model.Course;

@ApplicationScoped
public class CourseService {
    @Inject
    private CourseDao courseDao;
    
    @Transactional
    public List<Course> findAll() {
        return courseDao.findAll();
    }
    
    @Transactional
    public Course findById(int id) {
        return courseDao.find(id);
    }
    
    @Transactional
    public void create(Course course) {
        courseDao.create(course);
    }
    
    
    @Transactional
    public Course update(Course course) {
        return courseDao.update(course);
    }
    
    
    @Transactional
    public void delete(int id) {
        courseDao.delete(id);
    }
}

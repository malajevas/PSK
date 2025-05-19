package com.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

import com.example.dao.StudentDao;
import com.example.model.Student;

@ApplicationScoped
public class StudentService {
    @Inject
    private StudentDao studentDao;
    
    
    @Transactional
    public List<Student> findAll() {
        return studentDao.findAll();
    }
    
    @Transactional
    public Student findById(int id) {
        Student student = studentDao.find(id);
        student.getCourses().size(); // Triggers lazy-loading
        return student;
    }
    
    @Transactional
    public void create(Student student) {
        studentDao.create(student);
    }
    
    @Transactional
    public Student update(Student student) {
        return studentDao.update(student);
    }
    
    @Transactional
    public void delete(int id) {
        studentDao.delete(id);
    }
    
    @Transactional
    public List<Student> findByUniversityId(int universityId) {
        return studentDao.findByUniversity(universityId);
    }
}

package com.example.dao;

import java.util.List;

import com.example.model.Student;

public interface StudentDao {
    List<Student> findAll();
    Student find(int id);
    void create(Student student);
    Student update(Student student);
    void delete(int id);
    List<Student> findByUniversity(int universityId);
}

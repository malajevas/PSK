package com.example.dao;

import java.util.List;

import com.example.model.University;

public interface UniversityDao {
    List<University> findAll();
    University find(int id);
    void create(University university);
    University update(University university);
    void delete(int id);
}

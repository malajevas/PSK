package com.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

import com.example.dao.UniversityDao;
import com.example.model.University;

@ApplicationScoped
public class UniversityService {
    @Inject
    private UniversityDao universityDao;
    
    @Transactional
    public List<University> findAll() {
        return universityDao.findAll();
    }
    
    @Transactional
    public University findById(int id) {
        return universityDao.find(id);
    }
    
    @Transactional
    public void create(University university) {
        universityDao.create(university);
    }
    
    @Transactional
    public University update(University university) {
        return universityDao.update(university);
    }
    
    @Transactional
    public void delete(int id) {
        universityDao.delete(id);
    }
}

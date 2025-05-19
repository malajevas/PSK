package com.example.dao;

import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Alternative;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

import com.example.model.University;

@Alternative
@Stateless
public class UniversityDaoJpa implements UniversityDao {
    @PersistenceContext(unitName = "UniversityPU")
    private EntityManager em;
    
    @Override
    public List<University> findAll() {
        return em.createQuery("SELECT u FROM University u", University.class)
                 .getResultList();
    }
    
    @Override
    public University find(int id) {
        return em.find(University.class, id);
    }
    
    @Override
    public void create(University university) {
        em.persist(university);
    }
    
    @Override
    public University update(University university) {
        return em.merge(university);
    }
    
    @Override
    public void delete(int id) {
        University u = em.find(University.class, id);
        if (u != null) {
            em.remove(u);  // Cascade will remove associated students and courses
        }
    }
}

package com.example.dao;

import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Alternative;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

import com.example.model.Course;
import com.example.model.Student;

@Alternative
@Stateless
public class CourseDaoJpa implements CourseDao {
    @PersistenceContext(unitName = "UniversityPU")
    private EntityManager em;
    
    @Override
    public List<Course> findAll() {
        return em.createQuery("SELECT c FROM Course c", Course.class)
                 .getResultList();
    }

    @Override
    public Course find(int id) {
        return em.find(Course.class, id);
    }
    
    @Override
    public void create(Course course) {
        em.persist(course);
    }
    
    @Override
    public Course update(Course course) {
        return em.merge(course);
    }


    @Override
    public void delete(int id) {
        Course c = em.find(Course.class, id);
        if (c != null) {
            // Remove course from students course lists to maintain integrity
            for (Student s : c.getStudents()) {
                s.getCourses().remove(c);
            }
            em.remove(c);
        }
    }
}

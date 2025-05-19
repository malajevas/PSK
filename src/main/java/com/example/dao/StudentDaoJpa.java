package com.example.dao;

import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Alternative;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

import com.example.model.Course;
import com.example.model.Student;

@Alternative
@Stateless
public class StudentDaoJpa implements StudentDao {
    @PersistenceContext(unitName = "UniversityPU")
    private EntityManager em;
    
    @Override
    public List<Student> findAll() {
        return em.createQuery("SELECT s FROM Student s", Student.class)
                 .getResultList();
    }
    
    @Override
    public Student find(int id) {
        return em.find(Student.class, id);
    }
    
    @Override
    public void create(Student student) {
        em.persist(student);
    }
    
    @Override
    public Student update(Student student) {
        return em.merge(student);
    }
    
    @Override
    public void delete(int id) {
        Student s = em.find(Student.class, id);
        if (s != null) {
            // Remove student from any courses to avoid constraint issues
            for (Course c : s.getCourses()) {
                c.getStudents().remove(s);
            }
            em.remove(s);
        }
    }
    
    @Override
    public List<Student> findByUniversity(int universityId) {
        return em.createQuery(
                    "SELECT s FROM Student s WHERE s.university.id = :univId", 
                    Student.class)
                 .setParameter("univId", universityId)
                 .getResultList();
    }
}

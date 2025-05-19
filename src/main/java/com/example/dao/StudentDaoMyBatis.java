package com.example.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import com.example.mapper.StudentMapper;
import com.example.model.Student;

import java.util.List;

@Alternative
@ApplicationScoped
public class StudentDaoMyBatis implements StudentDao {

    @Inject
    private SqlSession sqlSession;

    @Override
    public List<Student> findAll() {
        return sqlSession.getMapper(StudentMapper.class).selectAll();
    }

    @Override
    public Student find(int id) {
        return sqlSession.getMapper(StudentMapper.class).selectById(id);
    }

    @Override
    public void create(Student student) {
        sqlSession.getMapper(StudentMapper.class).insert(student);
    }

    @Override
    public Student update(Student student) {
        sqlSession.getMapper(StudentMapper.class).update(student);
        return student;
    }

    @Override
    public void delete(int id) {
        sqlSession.getMapper(StudentMapper.class).deleteById(id);
    }

    @Override
    public List<Student> findByUniversity(int universityId) {
        return sqlSession.getMapper(StudentMapper.class).selectByUniversityId(universityId);
    }
}

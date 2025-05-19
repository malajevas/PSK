package com.example.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import com.example.mapper.UniversityMapper;
import com.example.model.University;

import java.util.List;


@Alternative
@ApplicationScoped
public class UniversityDaoMyBatis implements UniversityDao {

    @Inject
    private SqlSession sqlSession;

    @Override
    public List<University> findAll() {
        return sqlSession.getMapper(UniversityMapper.class).selectAll();
    }

    @Override
    public University find(int id) {
        return sqlSession.getMapper(UniversityMapper.class).selectById(id);
    }

    @Override
    public void create(University university) {
        sqlSession.getMapper(UniversityMapper.class).insert(university);
    }

    @Override
    public University update(University university) {
        sqlSession.getMapper(UniversityMapper.class).update(university);
        return university;
    }

    @Override
    public void delete(int id) {
        sqlSession.getMapper(UniversityMapper.class).deleteById(id);
    }
}

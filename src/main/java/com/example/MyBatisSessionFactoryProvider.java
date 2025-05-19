package com.example;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.inject.Produces;
import jakarta.annotation.PostConstruct;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;

import java.io.InputStream;

@Startup
@Singleton
public class MyBatisSessionFactoryProvider {

    private SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void init() {
        try (InputStream input = Resources.getResourceAsStream("mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);
            DataSource ds = (DataSource) new InitialContext().lookup("jdbc/mydb");
            Environment env = new Environment(
                "payara",
                sqlSessionFactory.getConfiguration().getEnvironment().getTransactionFactory(),
                ds
            );
            sqlSessionFactory.getConfiguration().setEnvironment(env);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize MyBatis", e);
        }
    }

    @Produces
    public SqlSessionFactory getFactory() {
        return sqlSessionFactory;
    }

    @Produces
    public SqlSession getSession() {
        return sqlSessionFactory.openSession(true);
    }
}


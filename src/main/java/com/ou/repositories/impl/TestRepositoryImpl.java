package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.Test;
import com.ou.repositories.QuestionRepository;
import com.ou.repositories.TestRepository;
import org.hibernate.query.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class TestRepositoryImpl implements TestRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private QuestionRepository questionRepositoryImpl;


    @Override
    public List<Test> getAllTests(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Test> query = builder.createQuery(Test.class);
        Root<Test> root = query.from(Test.class);
        query.select(root);
        Query<Test> q = session.createQuery(query);

        // Apply pagination
        if(params != null) {
            int page = Integer.parseInt(params.getOrDefault("page","1"));
            int pageSize = Integer.parseInt(params.getOrDefault("pageSize", String.valueOf(PAGE_SIZE)));
            int start = (page - 1) * pageSize;
            q.setMaxResults(pageSize);
            q.setFirstResult(start);
        }
        return q.getResultList();
    }

    @Override
    public Optional<Test> getTestById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Test test = session.get(Test.class, id);
        return Optional.ofNullable(test);
    }

    @Override
    public Test addTest(Test test) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(test);
        return test;
    }

    @Override
    public Test updateTest(Test test) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.update(test);
        return test;
    }

    @Override
    public boolean deleteTest(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Test test = session.get(Test.class, id);
        if (test != null) {
            session.delete(test);
            return true;
        }
        return false;
    }

    @Override
    public List<Test> getTestsByCourse(Integer courseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Test> query = builder.createQuery(Test.class);
        Root<Test> root = query.from(Test.class);
        query.select(root).where(builder.equal(root.get("courseId").get("id"), courseId));
        Query<Test> q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Test> searchTestsByName(String name) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query <Test> q = session.createNamedQuery("Test.findByName", Test.class);
        q.setParameter("name", "%" + name + "%");
        return q.getResultList();
    }

    @Override
    public List<Test> getTestsByCreatedDateRange(Date startDate, Date endDate) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Test> query = builder.createQuery(Test.class);
        Root<Test> root = query.from(Test.class);
        query.select(root).where(builder.between(root.get("createdAt"), startDate, endDate));
        Query<Test> q = session.createQuery(query);
        return q.getResultList();
    }
}

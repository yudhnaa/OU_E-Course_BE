package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.Test;
import com.ou.repositories.QuestionRepository;
import com.ou.repositories.TestRepository;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.query.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.*;

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
//        Session session = sessionFactory.getObject().getCurrentSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Test> query = builder.createQuery(Test.class);
//        Root<Test> root = query.from(Test.class);
//        query.select(root);
//        Query<Test> q = session.createQuery(query);
//
//        // Apply pagination
//        if(params != null) {
//            int page = Integer.parseInt(params.getOrDefault("page","1"));
//            int pageSize = Integer.parseInt(params.getOrDefault("pageSize", String.valueOf(PAGE_SIZE)));
//            int start = (page - 1) * pageSize;
//            q.setMaxResults(pageSize);
//            q.setFirstResult(start);
//        }
//        return q.getResultList();

        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Test> query = builder.createQuery(Test.class);
        Root<Test> root = query.from(Test.class);
        query.select(root);
        List<Predicate> predicates = buildSearchPredicates(builder, root, params);
        if(!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
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
        session.merge(test);
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
    public List<Test> getTestsByCourse(Integer courseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Test> query = builder.createQuery(Test.class);
        Root<Test> root = query.from(Test.class);
        query.select(root).where();
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("courseId").get("id"), courseId));
        // Apply filters if any

        predicates.addAll(buildSearchPredicates(builder,root, params));

        query.where(builder.and(predicates.toArray(new Predicate[0])));

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

    @Override
    public long countTestsInCourse(Integer courseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Test> root = query.from(Test.class);

        query.select(builder.count(root))
                .where(builder.equal(root.get("courseId").get("id"), courseId));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Test> root = query.from(Test.class);

        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }

        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    public boolean isLecturerOfTest(Integer lecturerId, Integer testId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Test> query = builder.createQuery(Test.class);
        Root<Test> root = query.from(Test.class);
        query.where(
                builder.and(
                        builder.equal(root.get("id"), testId),
                        builder.equal(root.get("createdByUserId").get("id"), lecturerId)
                )
        );
        List<Test> results = session.createQuery(query).getResultList();
        return !results.isEmpty();
    }

    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<Test> root, Map<String, String> filters) {
        if (filters == null || filters.isEmpty()) {
            return Collections.emptyList();
        }

        List<Predicate> predicates = new ArrayList<>();

        if (filters.containsKey("name")) {
            predicates.add(builder.like(root.get("name"), String.format("%%%s%%", filters.get("name"))));
        }

        if (filters.containsKey("createdAt")) {
            Date createdAt = new Date(Long.parseLong(filters.get("createdAt")));
            predicates.add(builder.equal(root.get("createdAt"), createdAt));
        }

        if( filters.containsKey("durationMinutes")) {
            predicates.add(builder.equal(root.get("durationMinutes"), Integer.valueOf(filters.get("durationMinutes"))));
        }

        if (filters.containsKey("maxScore")) {
            BigDecimal maxScore = new BigDecimal(filters.get("maxScore"));
            predicates.add(builder.equal(root.get("maxScore"), maxScore));
        }

        if (filters.containsKey("courseId")) {
            Integer courseId = Integer.parseInt(filters.get("courseId"));
            predicates.add(builder.equal(root.get("courseId").get("id"), courseId));
        }

        if(filters.containsKey("createdByUserId")){
            Integer createdByUserId = Integer.parseInt(filters.get("createdByUserId"));
            predicates.add(builder.equal(root.get("createdByUserId").get("id"), createdByUserId));
        }

        if(filters.containsKey("description")){
            predicates.add(builder.like(root.get("description"), String.format("%%%s%%", filters.get("description"))));
        }

        return predicates;
    }

}
